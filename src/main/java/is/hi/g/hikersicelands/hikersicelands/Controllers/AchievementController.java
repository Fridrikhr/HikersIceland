package is.hi.g.hikersicelands.hikersicelands.Controllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.Achievement;
import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Services.AchievementService;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import is.hi.g.hikersicelands.hikersicelands.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class AchievementController {

    private HikeService hikeService;
    private AchievementService achievementService;

    @Autowired
    public AchievementController(HikeService hikeService, AchievementService achievementService){
        this.hikeService = hikeService;
        this.achievementService = achievementService;
    }

    @RequestMapping(value ="/hike/{id}/achievement", method = RequestMethod.POST)
    public String addAchievement(@PathVariable("id") long id, @Valid Achievement achievement, BindingResult result, Model model){
        if(result.hasErrors()){
            return "welcome";
        }
        // find the hike
        Hike hike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        // create a new Achievement
        Achievement saveAchievement = new Achievement(achievement.getName(), achievement.getDescription(), achievement.getDifficulty(), hike);
        achievementService.save(saveAchievement);

        Hike updatedHike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", updatedHike);
        model.addAttribute("achievement", new Achievement());
        return "hike";
    }

    @RequestMapping(value ="/hike/{hikeid}/achievement/{achievementid}", method = RequestMethod.POST)
    public String deleteAchievement(@PathVariable("hikeid") long hikeid, @PathVariable("achievementid") long id, @Valid Achievement achievement, BindingResult result, Model model){
        if(result.hasErrors()){
            return "welcome";
        }

        achievementService.deleteAchievementById(id);
        Hike hike = hikeService.findById(hikeid).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", hike);
        model.addAttribute("achievement", new Achievement());
        return "Hike";
    }

    @RequestMapping(value ="/hike/{hikeid}/achievement/{achievementid}/complete", method = RequestMethod.POST)
    public String completeAchievement(@PathVariable("hikeid") long hikeid, @PathVariable("achievementid") long id, @Valid Achievement achievement, BindingResult result, Model model){
        if(result.hasErrors()){
            return "welcome";
        }

        // TODO complete hike for a user

        Hike hike = hikeService.findById(hikeid).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", hike);
        model.addAttribute("achievement", new Achievement());
        return "Hike";
    }

    @RequestMapping(value="/hike/{id]/achievement", method = RequestMethod.GET)
    public String addAchievementForm(Hike hike){
        return "welcome";
    }

}