package notebook

import org.springframework.dao.DataIntegrityViolationException

class PlacementController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [placementInstanceList: Placement.list(params), placementInstanceTotal: Placement.count()]
    }

    def create() {
        [placementInstance: new Placement(params)]
    }

    def save() {
        def placementInstance = new Placement(params)
        if (!placementInstance.save(flush: true)) {
            render(view: "create", model: [placementInstance: placementInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'placement.label', default: 'Placement'), placementInstance.id])
        redirect(action: "show", id: placementInstance.id)
    }

    def show() {
        def placementInstance = Placement.get(params.id)
        if (!placementInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'placement.label', default: 'Placement'), params.id])
            redirect(action: "list")
            return
        }

        [placementInstance: placementInstance]
    }

    def edit() {
        def placementInstance = Placement.get(params.id)
        if (!placementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'placement.label', default: 'Placement'), params.id])
            redirect(action: "list")
            return
        }

        [placementInstance: placementInstance]
    }

    def update() {
        def placementInstance = Placement.get(params.id)
        if (!placementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'placement.label', default: 'Placement'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (placementInstance.version > version) {
                placementInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'placement.label', default: 'Placement')] as Object[],
                          "Another user has updated this Placement while you were editing")
                render(view: "edit", model: [placementInstance: placementInstance])
                return
            }
        }

        placementInstance.properties = params

        if (!placementInstance.save(flush: true)) {
            render(view: "edit", model: [placementInstance: placementInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'placement.label', default: 'Placement'), placementInstance.id])
        redirect(action: "show", id: placementInstance.id)
    }

    def delete() {
        def placementInstance = Placement.get(params.id)
        if (!placementInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'placement.label', default: 'Placement'), params.id])
            redirect(action: "list")
            return
        }

        try {
            placementInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'placement.label', default: 'Placement'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'placement.label', default: 'Placement'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
