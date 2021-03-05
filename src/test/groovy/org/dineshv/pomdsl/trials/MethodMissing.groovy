package org.dineshv.pomdsl.trials

class Class1 {

   def methodMissing(String name, args) {


      if(name == 'm' && args.size() == 1 && args[0].class.name == 'java.lang.String'){
         println "name=$name args=${args[0]} args_type=${args[0].class.name}"
      }
      else {
         println 'Invalid method'
      }
   }
}

Class1 c = new Class1()

c.m()
c.m('dinesh')
c.m(3)