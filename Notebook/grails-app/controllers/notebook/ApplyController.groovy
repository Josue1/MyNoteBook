package notebook

import org.springframework.dao.DataIntegrityViolationException

class ApplyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [applyInstanceList: Apply.list(params), applyInstanceTotal: Apply.count()]
    }

    def create() {
        [applyInstance: new Apply(params)]
    }

    def save() {
        def applyInstance = new Apply(params)
        if (!applyInstance.save(flush: true)) {
            render(view: "create", model: [applyInstance: applyInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'apply.label', default: 'Apply'), applyInstance.id])
        redirect(action: "show", id: applyInstance.id)
    }

    def show() {
        def applyInstance = Apply.get(params.id)
        if (!applyInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'apply.label', default: 'Apply'), params.id])
            redirect(action: "list")
            return
        }

        [applyInstance: applyInstance]
    }

    def edit() {
        def applyInstance = Apply.get(params.id)
        if (!applyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'apply.label', default: 'Apply'), params.id])
            redirect(action: "list")
            return
        }

        [applyInstance: applyInstance]
    }

    def update() {
        def applyInstance = Apply.get(params.id)
        if (!applyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'apply.label', default: 'Apply'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (applyInstance.version > version) {
                applyInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'apply.label', default: 'Apply')] as Object[],
                          "Another user has updated this Apply while you were editing")
                render(view: "edit", model: [applyInstance: applyInstance])
                return
            }
        }

        applyInstance.properties = params

        if (!applyInstance.save(flush: true)) {
            render(view: "edit", model: [applyInstance: applyInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'apply.label', default: 'Apply'), applyInstance.id])
        redirect(action: "show", id: applyInstance.id)
    }

    def delete() {
        def applyInstance = Apply.get(params.id)
        if (!applyInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'apply.label', default: 'Apply'), params.id])
            redirect(action: "list")
            return
        }

        try {
            applyInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'apply.label', default: 'Apply'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'apply.label', default: 'Apply'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
