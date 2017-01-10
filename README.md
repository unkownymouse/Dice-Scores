# Dice Scores


It solves the generating function for enumerating the sum of dice scores when M dice with each N sides are rolled.

---

# Generating Function:
![Generating Function](https://cloud.githubusercontent.com/assets/15038803/21825384/896f674a-d7a9-11e6-8aec-17ae665d5882.png)

### Reducing The Equation:

![Reducing the equation](https://cloud.githubusercontent.com/assets/15038803/21825461/d92a6a82-d7a9-11e6-8a23-e9e4a7dbc3d9.png)

---

# Usage

```java
    import SkrewEverything.FindCoefficients
```
Either copy the `SkrewEverything` folder to your source folder or add the `FindCoefficients.jar` to your project

---

# Example
```java
    import SkrewEverything.FindCoefficients;
    
    public class Example
    {
        public static void main(String[] args)
        {
            FindCoefficients fc = new FindCoefficients(6,2); // First: sides, Second: no. of dice
            ArrayList<BigInteger> al = fc.getCoefficients();
            String equation = fc.getEquation();
            int index = fc.indexOfPower(2); //Returns the index at which the coefficient of specified power is there in List
        }
    }
```

License
----

MIT


**Free Software, Hell Yeah!**

