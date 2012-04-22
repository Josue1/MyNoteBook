package notebook



import org.junit.*
import grails.test.mixin.*

@TestFor(StudentController)
@Mock(Student)
class StudentControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/student/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.studentInstanceList.size() == 0
        assert model.studentInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.studentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.studentInstance != null
        assert view == '/student/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/student/show/1'
        assert controller.flash.message != null
        assert Student.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/student/list'


        populateValidParams(params)
        def student = new Student(params)

        assert student.save() != null

        params.id = student.id

        def model = controller.show()

        assert model.studentInstance == student
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/student/list'


        populateValidParams(params)
        def student = new Student(params)

        assert student.save() != null

        params.id = student.id

        def model = controller.edit()

        assert model.studentInstance == student
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/student/list'

        response.reset()


        populateValidParams(params)
        def student = new Student(params)

        assert student.save() != null

        // test invalid parameters in update
        params.id = student.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/student/edit"
        assert model.studentInstance != null

        student.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/student/show/$student.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        student.clearErrors()

        populateValidParams(params)
        params.id = student.id
        params.version = -1
        controller.update()

        assert view == "/student/edit"
        assert model.studentInstance != null
        assert model.studentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/student/list'

        response.reset()

        populateValidParams(params)
        def student = new Student(params)

        assert student.save() != null
        assert Student.count() == 1

        params.id = student.id

        controller.delete()

        assert Student.count() == 0
        assert Student.get(student.id) == null
        assert response.redirectedUrl == '/student/list'
    }
}
