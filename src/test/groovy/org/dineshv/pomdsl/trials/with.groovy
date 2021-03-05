package org.dineshv.pomdsl.trials

class TestClass {
   int a, b, c

   TestClass(a, b, c) {
      this.a = a
      this.b = b
      this.c = c
   }
}

TestClass tc = new TestClass(1, 2, 3)

int x, y, z

tc.with {
   x = a
   y = b
   z = c
}

println("x = $x, y = $y, z = $z")