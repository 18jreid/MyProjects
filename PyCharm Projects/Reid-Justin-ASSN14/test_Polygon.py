import unittest
from random import randint
# Maks sure this is importing the class from your polygon file.
from polygon import Polygon
# This is the only line you should need to change to make sure it works

class TestPolygon(unittest.TestCase):
    def test_addition(self):
        a = randint(1, 10)
        p1 = Polygon(a)
        b = randint(1, 10)
        p2 = Polygon(b)
        self.assertEqual(a + b, p1 + p2)

    def test_subtraction(self):
        a = randint(1, 10)
        p1 = Polygon(a)
        b = randint(1, 10)
        p2 = Polygon(b)
        self.assertEqual(a - b, p1 - p2)

    def test_greater(self):
        a = randint(1, 10)
        p1 = Polygon(a)
        b = randint(1, 10)
        p2 = Polygon(b)
        self.assertEqual(a > b, p1 > p2)

    def test_lesser(self):
        a = randint(1, 10)
        p1 = Polygon(a)
        b = randint(1, 10)
        p2 = Polygon(b)
        self.assertEqual(a < b, p1 < p2)

    def test_equal(self):
        a = randint(1, 10)
        p1 = Polygon(a)
        b = randint(1, 10)
        p2 = Polygon(b)
        self.assertEqual(a == b, p1 == p2)

    def test_length(self):
        a = randint(1, 10)
        p = Polygon(a)
        self.assertEqual(a, len(p))

if __name__ == '__main__':
    unittest.main()
