package springtest.chap11.survey;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	@GetMapping
	public String form(Model model) {
		List<Question> questions = createQuestions();
		model.addAttribute("questions", questions);		//뷰에 데이터 전달
		return "survey/surveyForm";
	}
	
	@PostMapping
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		return "survey/submitted";
	}
	
	private List<Question> createQuestions(){
		Question q1 = new Question("당신의 역할은?", Arrays.asList("서버", "프론트", "풀스택"));
		Question q2 = new Question("개발도구는?", Arrays.asList("Eclipse", "Intellij", "Sublime"));
		Question q3 = new Question("하고 싶은말?");
		return Arrays.asList(q1, q2, q3);
	}
}
