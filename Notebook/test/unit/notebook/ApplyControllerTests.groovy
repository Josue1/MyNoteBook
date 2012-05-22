package notebook



import org.junit.*
import grails.test.mixin.*

@TestFor(ApplyController)
@Mock(Apply)
class ApplyControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/apply/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.applyInstanceList.size() == 0
        assert model.applyInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.applyInstance != null
    }

    void testSave() {
        controller.save()

        assert model.applyInstance != null
        assert view == '/apply/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/apply/show/1'
        assert controller.flash.message != null
        assert Apply.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/apply/list'


        populateValidParams(params)
        def apply = new Apply(params)

        assert apply.save() != null

        params.id = apply.id

        def model = controller.show()

        assert model.applyInstance == apply
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/apply/list'


        populateValidParams(params)
        def apply = new Apply(params)

        assert apply.save() != null

        params.id = apply.id

        def model = controller.edit()

        assert model.applyInstance == apply
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/apply/list'

        response.reset()


        populateValidParams(params)
        def apply = new Apply(params)

        assert apply.save() != null

        // test invalid parameters in update
        params.id = apply.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/apply/edit"
        assert model.applyInstance != null

        apply.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/apply/show/$apply.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        apply.clearErrors()

        populateValidParams(params)
        params.id = apply.id
        params.version = -1
        controller.update()

        assert view == "/apply/edit"
        assert model.applyInstance != null
        assert model.applyInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/apply/list'

        response.reset()

        populateValidParams(params)
        def apply = new Apply(params)

        assert apply.save() != null
        assert Apply.count() == 1

        params.id = apply.id

        controller.delete()

        assert Apply.count() == 0
        assert Apply.get(apply.id) == null
        assert response.redirectedUrl == '/apply/list'
    }
}
