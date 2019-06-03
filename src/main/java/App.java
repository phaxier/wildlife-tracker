import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animals.all());
            model.put("endangeredAnimals", EndangeredAnimals.all());
            model.put("sightings", Sightings.all());
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/endangered_sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangerName = request.queryParams("rangerName");
            int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
            String latLong = request.queryParams("latLong");
            Sightings sightings = new Sightings(animalIdSelected, latLong, rangerName);
            sightings.save();
            model.put("sightings", sightings);
            model.put("animals", EndangeredAnimals.all());
            String animal = EndangeredAnimals.find(animalIdSelected).getName();
            model.put("animals", animal);
            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangerName = request.queryParams("rangerName");
            int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));
            String latLong = request.queryParams("latLong");
            Sightings sightings = new Sightings(animalIdSelected, latLong, rangerName);
            sightings.save();
            model.put("sightings", sightings);
            model.put("animals", Animals.all());
            String animals = Animals.find(animalIdSelected).getName();
            model.put("animals", animals);
            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animals.all());
            model.put("endangeredAnimals", EndangeredAnimal.all());
            model.put("template", "templates/animal-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            boolean endangered = request.queryParamsValues("endangered")!=null;
            if (endangered) {
                String name = request.queryParams("name");
                String health = request.queryParams("health");
                String age = request.queryParams("age");
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
                endangeredAnimal.save();
                model.put("animals", Animals.all());
                model.put("endangeredAnimals", EndangeredAnimal.all());
            } else {
                String name = request.queryParams("name");
                Animals animals = new Animals(name);
                animals.save();
                model.put("animals", Animals.all());
                model.put("endangeredAnimals", EndangeredAnimal.all());
            }
            response.redirect("/");
            return null;
        });

        get("/animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Animals animals = Animals.find(Integer.parseInt(request.params("id")));
            model.put("animal", animals);
            model.put("template", "templates/animal.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/endangered_animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(request.params("id")));
            model.put("endangeredAnimal", endangeredAnimal);
            model.put("template", "templates/endangered_animal.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/error", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/error.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}
