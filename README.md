![Cerealib Banner](./cerealibBanner.png)

## Cerealib - A High Performance Serialization Library for Java

Cerealib is a fast serialization library for Java which supports all Java Primitives, Strings, and Primitive Arrays - and offers many performance benefits over the default Java Serialization implementation.

For real world usage and reference, try out the code in the [CerealibSandbox](./CerealibSandbox/src) module!

# Usage Guide

## Concepts:

### CLDatabase

Every serialized file is referred to as a CLDatabase. A CLDatabase contains all the information needed to write / read the
data to / from a binary file.

A CLDatabase is made up of multiple CLObjects.

- ### CLObjects
  A CLObject is an entity, which can contain related *CLFields*, *CLStrings*, *CLArrays*. Think of it as a class / class
  object, that contains all the different properties related to that instance / object.
    - ### CLField
        - CLFields correspond to the Java Primitives such as **Byte, Short, Char, Int, Long, Float, Double, Boolean**.
        - When you want to serialize one of the Java primitives, you use one of these CLFields.

    - ### CLString
        - Since Strings are not a Primitive Java type, you need to use the special CLString to serialize a string.

    - ### CLArray
        - Whenever you have an array sequence of a particular type, you need to use the CLArray class to serialize it.

## Serializing:

Create a new CLDatabase instance

```java
CLDatabase database = new CLDatabase("Database");
```

Create an object to store in the database (you can have as many of these as you want in a single CLDatabase)

```java
CLObject object = new CLObject("ObjectName"); 
```

Start adding CLFields, CLArrays, CLStrings to the object

```java
// To add a string to the object, we use the Create method in the CLString class
object.addString(CLString.Create("colour", "red"));

// To add one of the primitive types, we call the corresponding method on the CLField class.
object.addField(CLField.Integer("age", 24));
object.addField(CLField.Boolean("ranked", true));
object.addField(CLField.Short("num", 50));
object.addField(CLField.Byte("byteNum", 4));
object.addField(CLField.Double("doubleNum", 234.35));
// and so on...
        
        
// Adding an array sequence to the object.
        
// Generating a random integer array.
int[] data = new int[50000];
for(int i = 0; i < data.length; ++i){
    data[i] = random.nextInt();
}
// We add a CLArray object, using the Integer method (since we are adding an integer array) to the database.
Object.addField(CLArray.Integer("Random Numbers",data))

// Generating a random boolean array.
boolean[] bools = new boolean[50];
for(int i = 0; i < bools.length; ++i){
    bools[i]=random.nextBoolean();
}
// We add a CLArray object, using the Boolean method (since we are adding a boolean array) to the database.
Object.addField(CLArray.Boolean("Random Bools",bools));
```
Add the object to the database
```java
database.addObject(object);
```
- Serialize the database to a file
    ```java
    database.serializeToFile("SerializedFile.cld");     // File extension can be anything.
    ```
- Serialize into a byte array
    ```java
    // Create a destination to store the byte stream.
    byte[] data = new byte[database.getSize()];
    
    // data is the destination array, 0 is the offset from where we start writing inside the data array.
    database.getBytes(data, 0);     
    ```
  
## Deserializing
Read the binary file into a CLDatabase
```java
CLDatabase database = CLDatabase.deserializeFromFile("SerializedFile.cld");
```
After this, the objects can be access through a variety of methods.
```java
// You can load a CLObject from the database by providing its name
CLObject object = database.findObject("ObjectName");

// You can load the CLFields, CLArrays, CLStrings from the object now
int loadedAge = object.findField("age").getInt();
short loadedNum = object.findField("num").getShort();
boolean loadedRanked = object.findField("ranked").getBoolean();

String loadedColour = object.findString("colour").getString();

// In case you don't know the size of the array you're about to read - you can write that as well to a field while serializing.
// So, when you read the data back, you can read that field and use that to instantiate a new array of the required size.
boolean[] loadedBools = new boolean[50];
loadedBools = object.findArray("Random Bools").getBooleanData();
```