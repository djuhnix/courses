package courses.client.app;

public class testToJsonClass {
        private int id;
        private String name;
        private String color;
        private String eyecolor;
        private String breed;
        public testToJsonClass() {
        this.id =0;
        this.name = "minou";
        }
     public testToJsonClass(int id, String name) {
         this.id = id;
         this.name = name;
     }
                // Getters & Setters
                @Override
                public String toString() {
                    return "testToJsonClass{" +
                            "id=" + id +
                            ", name='" + name +
                            '\'' +
                            '}';
                }
                    public int getId() { return id; }
                    public void setId(int id) { this.id = id; }
                    public String getName() { return name; }
                    public void setName(String name) { this.name = name; }
                    public String getColor() {  return color; }
                    public void setColor(String color) { this.color = color; }
                    public String getEyecolor() { return eyecolor;}
                        public void setEyecolor(String eyecolor) { this.eyecolor = eyecolor; }
                        public String getBreed() {  return breed; }
                        public void setBreed(String breed) { this.breed = breed; }
                    }

