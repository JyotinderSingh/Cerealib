/**
 * Real World Usage guide.
 * This file demonstrates how you might want to use this library in a real world scenario - in an Object Oriented Setting.
 */

import com.cerealib.CLDatabase;
import com.cerealib.CLField;
import com.cerealib.CLObject;
import com.cerealib.CLString;

import java.util.ArrayList;
import java.util.List;

public class Sandbox {
    static class Level {
        private String name;
        private String path;
        private int width, height;
        private List<Entity> entities = new ArrayList<Entity>();

        private Level() {
        }

        public Level(String path) {
            this.name = "Level 1";
            this.path = path;
            this.width = 64;
            this.height = 128;
        }

        public void add(Entity e) {
            entities.add(e);
            e.init(this);
        }

        public void update() {
        }

        public void render() {
        }

        // SERIALIZATION HANDLER
        public void save(String path) {
            CLDatabase database = new CLDatabase(this.name);

            // We create an object for the level.
            CLObject object = new CLObject("LevelData");
            object.addString(CLString.Create("name", name));
            object.addString(CLString.Create("path", this.path));
            object.addField(CLField.Integer("width", width));
            object.addField(CLField.Integer("height", height));
            // We add an entityCount field to the binary to make our lives easier when trying to read the data.
            object.addField(CLField.Integer("entityCount", entities.size()));
            // Add the level object to the database.
            database.addObject(object);

            // We iterate over all the entities in this level, serializing them as we go.
            // We store each entity as a separate object.
            for (int i = 0; i < entities.size(); ++i) {
                // Fetch the entity to be serialized.
                Entity e = entities.get(i);

                // We create a CLObject instance to store that entity.
                CLObject entity = new CLObject("E" + i);

                // We also want to store the type of the entity we are serializing. (Entity or a Player)
                byte type = 0;
                if (e instanceof Player) {
                    type = 1;
                }
                // Add the type information.
                entity.addField(CLField.Byte("type", type));

                // We call the entity's serialize method, which adds all its fields to the CLObject instance we created.
                e.serialize(entity);

                // Once the object is built, we add it to the database.
                database.addObject(entity);
            }

            // Finally we serialize the database into a file.
            database.serializeToFile(path);
        }

        // DESERIALIZATION HANDLER
        public static Level load(String path) {
            CLDatabase database = CLDatabase.deserializeFromFile(path);
            CLObject levelData = database.findObject("LevelData");

            Level result = new Level();
            result.name = levelData.findString("name").getString();
            result.path = levelData.findString("path").getString();
            result.width = levelData.findField("width").getInt();
            result.height = levelData.findField("height").getInt();
            int entityCount = levelData.findField("entityCount").getInt();

            for (int i = 0; i < entityCount; ++i) {
                CLObject entity = database.findObject("E" + i);
                Entity e;
                if (entity.findField("type").getByte() == 1) {
                    e = Player.deserialize(entity);
                } else {
                    e = Entity.deserialize(entity);
                }

                result.add(e);
            }

            return result;
        }

    }

    static class Entity {
        protected int x, y;
        protected boolean removed = false;
        protected Level level;

        public void Entity() {
            x = 0;
            y = 0;
        }

        public void init(Level level) {
            this.level = level;
        }

        // SERIALIZATION HANDLER
        public void serialize(CLObject object) {
            object.addField(CLField.Integer("x", x));
            object.addField(CLField.Integer("y", y));
            object.addField(CLField.Boolean("removed", removed));
        }

        // DESERIALIZATION HANDLER
        public static Entity deserialize(CLObject object) {
            Entity result = new Entity();
            result.x = object.findField("x").getInt();
            result.y = object.findField("y").getInt();
            result.removed = object.findField("removed").getBoolean();

            return result;
        }
    }

    static class Player extends Entity {
        private String name;
        private String avatarPath;

        private Player() {
        }

        public Player(String name, int x, int y) {
            this.x = x;
            this.y = y;
            this.name = name;
            avatarPath = "res/avatar.png";
        }

        // SERIALIZATION HANDLER
        @Override
        public void serialize(CLObject object) {
            super.serialize(object);
            object.addString(CLString.Create("name", name));
            object.addString(CLString.Create("avatarPath", avatarPath));
        }

        // DESERIALIZATION HANDLER
        public static Player deserialize(CLObject object) {
            Entity e = Entity.deserialize(object);
            Player result = new Player();
            result.x = e.x;
            result.y = e.y;
            result.removed = e.removed;

            result.name = object.findString("name").getString();
            result.avatarPath = object.findString("avatarPath").getString();

            return result;
        }
    }

    public void play() {
        // WRITING / SERIALIZING
        Entity mob = new Entity();
        Player player = new Player("CerealHero", 40, 28);
        Level writeLevel = new Level("res/level.lvl");
        writeLevel.add(mob);
        writeLevel.add(player);
        writeLevel.save("level.cld");

        // LOADING / DESERIALIZING
        Level loadLevel = Level.load("level.cld");
    }
}
