import notebook.Student
class BootStrap {

    def init = { servletContext ->
   def Josuestu = new Student(name:'Josue',courseCode:'439 247').save();
   def Linostu = new Student(name:'Lino',courseCode:'286 279').save();
   def Alexstu = new Student(name:'Alex',courseCode:'284 303').save();
    }
    
    def destroy = {
    }
}
