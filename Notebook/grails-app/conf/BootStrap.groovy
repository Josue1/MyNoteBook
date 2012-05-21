import notebook.Student
import notebook.Status
import notebook.Placement
import notebook.Application
class BootStrap {

    def init = { servletContext ->
    def Josuestu = new Student(name:'Josue',courseCode:'439 247').save();
   def Linostu = new Student(name:'Lino',courseCode:'286 279').save();
   def Alexstu = new Student(name:'Alex',courseCode:'284 303').save();

   def Josuestu2 = new Status(Code:'202 225',Description:'Available').save();
   def Linostu2 = new Status(Code:'205 289',Description:'Available').save();
   def Alexstu2 = new Status(Code:'208 237',Description:'Not Available').save();

   def Josuestu3 = new Placement(companyName:'companyName', jobTitle:'Web Designer').save();
def Linostu3 = new Placement(companyName:'companyName', jobTitle:'Programmer').save();
 def Alexstu3 = new Placement(companyName:'companyName', jobTitle:'Technician').save(); 
  

   
    }
    
    def destroy = {
    }
}
