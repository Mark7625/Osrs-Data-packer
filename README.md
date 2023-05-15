# Osrs Data Packer

Packs data and updates the osrs cache

## Features

- Xteas Removal
- Map Packer
- Model Packer
- Dat Packer for 317
- Ability to add your own Packer tasks with the api

# Usage

### Builder
- taskType = Sets the type of task you wish to run [BUILD,UPDATE_REV,RUN_JS5]
- extraTasks = A list of classes that extends CacheTask interface these are to be used for custom
tasks you wish to do when updating rev or repacking cache 
- configLocation = Location of your config file for the program
- cacheLocation = Location of the cache this will override the config file

### Built in Tasks

- Pack Dats [This pack any custom dats u may need for our 317 users]
- Pack Models
- Pack Maps
- Remove Xteas [This will remove any xteas from the default osrs cache]

---

### Properties File

Default location for props is "./packerConfig.properties" this can be changed
via the builder if you wish

```properties
        cacheLocation =
        revision=
```
- cacheLocation If you set cacheLocation in your builder you can ignore this as it will override the props file
- revision This is the revision of the cache your server runs on and will download from https://archive.openrs2.org/caches/, if none is set it will download the latest

---

### How to run Exmaples

<details>
  <summary>Running Js5</summary>

```java
        Application application = new Builder().
        taskType(TaskType.RUN_JS5).
        cacheLocation(new File(""))
        .build();

        application.initialize();
```
</details>

<details>
  <summary>Building your cache </summary>

```java
public class Test {
    public static void main(String[] args) {
        List<CacheTask> tasks = List.of(
                new PackMaps(new File("C:\\Users\\Administrator\\Desktop\\RSPS\\Group\\Group-JS5\\data\\custom\\maps")),
                new PackModels(new File("C:\\Users\\Administrator\\Desktop\\RSPS\\Group\\Group-JS5\\data\\custom\\models")),
                new PackDats(new File("C:\\Users\\Administrator\\Desktop\\RSPS\\Group\\Group-JS5\\data\\custom\\dats"))
        );
        Application application = new Builder().
                taskType(TaskType.BUILD).
                cacheLocation(new File("C:\\Users\\Administrator\\Desktop\\RSPS\\Group\\Group-JS5\\data\\cache\\")).
                extraTasks(tasks)
        .build();
        application.initialize();
    }
}
```

</details>

<details>
  <summary>Updating your rev</summary>

```java

public class Test3 {

    public static void main(String[] args) {
        List<CacheTask> tasks = List.of(
                new RemoveXteas(),
                new PackMaps(new File("C:\\Users\\Administrator\\Desktop\\RSPS\\Group\\Group-JS5\\data\\custom\\maps")),
                new PackModels(new File("C:\\Users\\Administrator\\Desktop\\RSPS\\Group\\Group-JS5\\data\\custom\\models")),
                new PackDats(new File("C:\\Users\\Administrator\\Desktop\\RSPS\\Group\\Group-JS5\\data\\custom\\dats"))
        );
        Application application = new Builder().
                taskType(TaskType.UPDATE_REV).
                cacheLocation(new File("C:\\Users\\Administrator\\Desktop\\RSPS\\Group\\Group-JS5\\data\\cache\\")).
                extraTasks(tasks)
                .build();
        application.initialize();

    }
}

```
</details>

---

### How to add custom tasks

If you want a custom task to pack custom types you can do this by making a class like 

```
class PackModels() : CacheTask() {
    override fun init(library: CacheLibrary) {
        
    }

}
```
library is the cache instance and you should always refer to this when editing the cache
once this class is made you just add it to the list in the builder

---

### Credits
- Displee [Cache Lib] [https://github.com/Displee/rs-cache-library]
- Jire [Js5 Server] [https://github.com/Jire]