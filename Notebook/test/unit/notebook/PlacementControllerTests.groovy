package notebook



import org.junit.*
import grails.test.mixin.*

@TestFor(PlacementController)
@Mock(Placement)
class PlacementControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/placement/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.placementInstanceList.size() == 0
        assert model.placementInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.placementInstance != null
    }

    void testSave() {
        controller.save()

        assert model.placementInstance != null
        assert view == '/placement/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/placement/show/1'
        assert controller.flash.message != null
        assert Placement.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/placement/list'


        populateValidParams(params)
        def placement = new Placement(params)

        assert placement.save() != null

        params.id = placement.id

        def model = controller.show()

        assert model.placementInstance == placement
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/placement/list'


        populateValidParams(params)
        def placement = new Placement(params)

        assert placement.save() != null

        params.id = placement.id

        def model = controller.edit()

        assert model.placementInstance == placement
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/placement/list'

        response.reset()


        populateValidParams(params)
        def placement = new Placement(params)

        assert placement.save() != null

        // test invalid parameters in update
        params.id = placement.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/placement/edit"
        assert model.placementInstance != null

        placement.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/placement/show/$placement.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        placement.clearErrors()

        populateValidParams(params)
        params.id = placement.id
        params.version = -1
        controller.update()

        assert view == "/placement/edit"
        assert model.placementInstance != null
        assert model.placementInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/placement/list'

        response.reset()

        populateValidParams(params)
        def placement = new Placement(params)

        assert placement.save() != null
        assert Placement.count() == 1

        params.id = placement.id

        controller.delete()

        assert Placement.count() == 0
        assert Placement.get(placement.id) == null
        assert response.redirectedUrl == '/placement/list'
    }
}
