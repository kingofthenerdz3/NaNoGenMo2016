import java.lang.StringBuilder;
import java.util.Random;
import java.util.ArrayList;

public class SherlockMystery {

	private final int TITLE_PATHS = 6;
	private final int INTRO_PATHS = 5;
	private final int SEARCH_PATHS = 5;
	private final int WATSON_WONDERS = 10;
	private final int TOTAL_SUSPECTS = 5;
	private final int TOTAL_INFORMANTS = 3;

	private Lexicon lexicon;
	private CrimeScene crime;

	private ArrayList<Person> suspects = new ArrayList<Person>();
	private ArrayList<Person> informants = new ArrayList<Person>();
	private Person culprit;
	private Person victim;
	private Person client;

	int inquiryCount = 0;

	Random r = new Random();

	public SherlockMystery(Lexicon lexicon) {
		this.lexicon = lexicon;
		this.setDetails();
	}

	public void setDetails() {

		getPeople(TOTAL_SUSPECTS + 1, suspects);
		getPeople(TOTAL_INFORMANTS + 1, informants);

		this.victim = getPerson();
		this.client = getPerson();
		this.culprit = suspects.get(r.nextInt(TOTAL_SUSPECTS));

		this.crime = getCrimeScene();

	}

	public String getTitle() {
		int choice = r.nextInt(this.TITLE_PATHS + 1);
		String title = "";
		switch (choice) {
		case 0:
			title += "The " + crime.getCrime() + " of " + victim.getFullName();
			break;
		case 1:
			title += "The " + getWord(lexicon.getAdjPerson()) + " " + crime.getCrime() + " in the " + crime.getPlace();
			break;
		case 2:
			title += "The Case of the " + getWord(lexicon.getDistinction()) + " " + crime.getCrime();
			break;
		case 3:
			title += "The " + getWord(lexicon.getAdjPerson()) + " " + crime.getWeapon();
			break;
		case 4:
			title += "The Case of the " + getWord(lexicon.getAdjPerson()) + " " + client.getProfession();
			break;
		case 5:
			title += "The Case of the " + crime.getCrime() + " in " + crime.getCity();
			break;
		case 6:
			title += "The " + crime.getCrime() + " of " + victim.getFullName();
			break;
		}

		return title;
	}

	public StringBuilder getIntro() {
		int intro = r.nextInt(this.INTRO_PATHS - 1);
		StringBuilder story = null;
		switch (intro) {
		case 0:
			story = getIntro1();
			break;
		case 1:
			story = getIntro2();
			break;
		case 2:
			story = getIntro3();
			break;
		case 3:
			story = getIntro4();
			break;
		case 4:
			story = getIntro5();
			break;

		}

		return story;

	}

	public StringBuilder getIntro1() {
		StringBuilder intro = new StringBuilder();
		intro.append("The " + getWord(lexicon.getSeason()) + " sky was a " + getWord(lexicon.getAdjObject()) + " "
				+ getWord(lexicon.getColor()) + " color. ");
		intro.append("Holmes had recently solved " + this.getTitle() + ". ");
		intro.append("He was " + getWord(lexicon.getPastime()) + " when I " + getWord(lexicon.getMovement())
				+ " into the room. ");
		intro.append("\n\"Watson! Read this.\" ");
		intro.append("\nHe " + getWord(lexicon.getTransVerb()) + " the Times. " + getWord(lexicon.getPreposition())
				+ " our " + getWord(lexicon.getObject()) + ". ");
		intro.append("An article was circled in " + getWord(lexicon.getColor()) + ". ");
		intro.append("It read: ");
		intro.append(this.getArticle());
		intro.append("\"It is obvious the " + crime.getCrime() + " was committed by " + suspects.get(1).getFullName()
				+ ". ");
		intro.append("Only a " + suspects.get(r.nextInt(TOTAL_SUSPECTS)).getObject() + " could have led to this "
				+ crime.getCrime() + ". \"");
		intro.append("\n\"Obvious? " + getWord(lexicon.getUnimpressed()) + " We shall see.\"");

		intro.append(getPlea());

		return intro;
	}

	public StringBuilder getIntro2() {
		StringBuilder intro = new StringBuilder();
		intro.append("I was searching for my " + getWord(lexicon.getObject())
				+ " in my shared rooms at 221B Baker Street. ");
		intro.append("My flatmate, Mr. Sherlock Holmes had left a " + getWord(lexicon.getAdjObject()) + ", "
				+ getWord(lexicon.getColor()) + " pile of " + getWord(lexicon.getEvidence())
				+ " experimentation tools in my path. ");
		intro.append("I " + getWord(lexicon.getSense())
				+ " the paper arriving and changed course, avoiding another mess of what might have been a "
				+ getWord(lexicon.getEvidence()) + " experiment. ");
		intro.append("I picked up the paper and saw the front page: ");
		intro.append(this.getArticle());
		intro.append("\"Holmes!\" I " + getWord(lexicon.getSpeak()) + " as I " + getWord(lexicon.getTransVerb())
				+ " the paper, \"You must read this " + getWord(lexicon.getUrgent()) + ".\" ");
		intro.append("\nHe " + getWord(lexicon.getMovement()) + " into the room, and took the paper. ");
		intro.append("\"" + getWord(lexicon.getComment()) + " " + getWord(lexicon.getComment()) + " "
				+ victim.getFullName() + ", the noteworthy " + victim.getProfession() + "? Victim to a "
				+ crime.getCrime() + "? At last! A mystery worthy of my attention!\" ");

		intro.append(getPlea());

		return intro;
	}

	public StringBuilder getIntro3() {
		Person suspect = suspects.get(r.nextInt(TOTAL_SUSPECTS));

		StringBuilder intro = new StringBuilder();
		intro.append("Lestrade, the famous Scotland Yard detective, " + getWord(lexicon.getMovement())
				+ " into the my flat at 221B Baker Street. ");
		intro.append("\n\"Mr. Holmes!\" he " + getWord(lexicon.getSpeak()) + ", \"Watson! Where is he?\"");
		intro.append("\n\"I am here, Lestrade.\" Holmes looked up from his " + getWord(lexicon.getAdjObject()) + " "
				+ getWord(lexicon.getEvidence()) + " experiment. ");
		intro.append("\n\"You must read this at once!\" Lestrade " + getWord(lexicon.getTransVerb())
				+ " the paper at Holmes. ");
		intro.append(this.getArticle());
		intro.append("\n\"You must help us convict " + suspect.getFullName() + ", the famous " + suspect.getProfession()
				+ " criminal!\" ");
		intro.append("\n\"" + getWord(lexicon.getUnimpressed()) + " I may " + getWord(lexicon.getThink()) + " the "
				+ crime.getCrime() + ". But not on your behalf. It is always folly to theorize before one has data.\"");
		intro.append("\n\"" + getWord(lexicon.getGreeting()) + ", Lestrade.\" ");
		intro.append("\nAs Lestrade " + getWord(lexicon.getMovement()) + " out, he " + getWord(lexicon.getSpeak())
				+ " about Holmes' methods.");
		intro.append("\"Really, Holmes!\" I began, but Holmes " + getWord(lexicon.getTransVerb()) + " his "
				+ getWord(lexicon.getDistinction()) + ". I " + getWord(lexicon.getMovement()) + " impatiently. ");

		intro.append(getPlea());

		return intro;
	}

	public StringBuilder getIntro4() {
		StringBuilder intro = new StringBuilder();
		intro.append("\"Watson - you have come just at the right moment. Lestrade and I were discussing this.\" ");
		intro.append("\nHe handed me the Times, an article marked in " + getWord(lexicon.getColor()) + " ink. ");
		intro.append(getArticle());
		intro.append("\n\"" + victim.getFullName() + "? The famous " + victim.getProfession() + "? ");
		intro.append("This is unbelievable!\"");
		intro.append("\n\"Not all all, my dear Watson. Even " + victim.getProfession() + "s have secrets. "
				+ getWord(lexicon.getComment()) + ".\" ");
		intro.append("\n\"I must " + getWord(lexicon.getThink()) + " this for some time. Lestrade, please return in "
				+ r.nextInt(3) + " days.\"");
		intro.append("\nLestrade " + getWord(lexicon.getMovement()) + " out, \"" + getWord(lexicon.getGreeting())
				+ " Holmes. See you then.\"");
		intro.append("\nHolmes " + getWord(lexicon.getMovement())
				+ " toward me. \"I think we will hear more about this business quite soon, Watson.\" ");

		intro.append(getPlea());

		return intro;
	}

	public StringBuilder getIntro5() {
		StringBuilder intro = new StringBuilder();
		intro.append("\"The sounds of " + getWord(lexicon.getPastime())
				+ " filled the air of 221B Baker Street, as they had for weeks. ");
		intro.append(
				"There was no living with my flatmate and companion, Mr. Sherlock Holmes, when he had no case to occupy his mind. ");
		intro.append("The flat was " + getWord(lexicon.getAdjObject()) + " and " + getWord(lexicon.getAdjObject())
				+ ". My friend needed a case.");
		intro.append("The sound stopped. My companion " + getWord(lexicon.getMovement()) + " in. ");
		intro.append(this.getPlea());
		intro.append("\n\n Our guest left and I looked up. \"The details of the case are familiar?\"");
		intro.append("\n\"Page " + r.nextInt(13) + ". Read it " + getWord(lexicon.getUrgent()) + "\" ");

		intro.append(
				"\nI flipped to the page in question and read as Holmes made travel arrangements with Mrs. Hudson, our landlady.");
		intro.append(getArticle());
		intro.append("\n\"Holmes, " + getWord(lexicon.getConfused()) + ". \"");
		intro.append("\n\"We will unwravel it! Now, we must make our inquiries.\"");

		return intro;
	}

	public StringBuilder getArticle() {
		StringBuilder article = new StringBuilder();
		article.append("\n\n.   .   .   .   .   .   .   .   .   .\n\n");
		article.append("The Times reported a certain " + victim.getFullName());
		article.append(" fell victim to " + crime.getCrime() + " " + crime.getTime() + " in the " + crime.getPlace()
				+ " of a local " + victim.getProfession() + " shop in the heart of " + crime.getCity() + ". ");
		article.append(
				" The victim was a " + getWord(lexicon.getAdjPerson()) + " local " + getWord(lexicon.getProfession()) + ". ");
		article.append(
				victim.getFirst() + " was a well-known employee of a prominent " + getWord(lexicon.getAdjObject()) + " "
						+ victim.getProfession() + " business in " + crime.getCity() + ". ");
		article.append(informants.get(TOTAL_INFORMANTS).getFullName() + " reported a " + getWord(lexicon.getColor())
				+ " " + crime.getWeapon() + " was seen in the " + crime.getPlace()
				+ " earlier. Official witnesses reported ");
		article.append(suspects.get(TOTAL_SUSPECTS - 0).getFullName() + " " + getWord(lexicon.getTransVerb()) + " a "
				+ suspects.get(TOTAL_SUSPECTS - 0).getObject() + " and ");
		article.append(suspects.get(TOTAL_SUSPECTS - 4).getFullName() + " " + getWord(lexicon.getTransVerb()) + " "
				+ suspects.get(TOTAL_SUSPECTS - 4).getObject() + ". ");
		article.append("Other sources reported ");
		article.append(suspects.get(TOTAL_SUSPECTS - 1).getFullName() + " " + getWord(lexicon.getTransVerb()) + " a "
				+ suspects.get(TOTAL_SUSPECTS - 1).getObject() + ", ");
		article.append(suspects.get(TOTAL_SUSPECTS - 2).getFullName() + " " + getWord(lexicon.getTransVerb()) + " a "
				+ suspects.get(TOTAL_SUSPECTS - 2).getObject() + ", and ");
		article.append(suspects.get(TOTAL_SUSPECTS - 5).getFullName() + " " + getWord(lexicon.getTransVerb()) + " a "
				+ suspects.get(TOTAL_SUSPECTS - 5).getObject() + " within hours of the murder. ");
		article.append("Meanwhile, unconfirmed rumors suggest " + suspects.get(TOTAL_SUSPECTS - 5).getFirst() + " "
				+ getWord(lexicon.getTransVerb()) + " a " + crime.getWeapon());
		article.append(" early " + crime.getTime() + ". The " + crime.getCity() + " Gazette reported several "
				+ crime.getWeapon() + " containers were stoled from a nearby shop by people fitting the description of "
				+ suspects.get(TOTAL_SUSPECTS - 5).getFullName() + " and "
				+ suspects.get(TOTAL_SUSPECTS - 2).getFullName() + ".");
		article.append("\n\n.   .   .   .   .   .   .   .   .   .\n\n");
		return article;
	}

	public StringBuilder getPlea() {
		StringBuilder plea = new StringBuilder();
		plea.append("\n\nHolmes cocked his head and " + getWord(lexicon.getMovement()) + " to the window. ");
		plea.append("\"Mrs. Hudson, bring tea! I believe we will have a guest before long.\"");
		plea.append("\n\n Moments later, a " + getWord(lexicon.getAdjPerson()) + ", " + getWord(lexicon.getAdjPerson())
				+ " person " + " " + getWord(lexicon.getMovement()) + " in. ");
		plea.append("\n\"I see you are a " + client.getProfession() + " who recently ");
		plea.append(getWord(lexicon.getTransVerb()) + " a " + getWord(lexicon.getObject()) + ".\"");
		plea.append("\n\"Sir, I have no time for your tricks! I am " + client.getFullName() + ". ");
		plea.append("Please listen, it is the " + crime.getCrime() + " - I have information!\"");
		plea.append("\n\"The details of the case are familiar to me. My investigation has already begun.\" ");
		plea.append("\n\"But Mr. Holmes, there are details that have not been reported!\"");
		plea.append("\n\"Go on,\" Our visitor had captured his attention. ");
		plea.append("\nListen - " + crime.getTime() + ", I saw " + suspects.get(r.nextInt(TOTAL_SUSPECTS)).getFullName()
				+ " with a " + crime.getWeapon() + ". ");
		plea.append("I tried to report it, but no one at the Yard would listen to me.\"");
		plea.append("\nHolmes paused. \"Has the " + crime.getWeapon() + " been recovered?\"");
		plea.append("\n\"" + getWord(lexicon.getReply())
				+ " Even so, I know the Yard are missing other facts! Will you help?\"");
		plea.append("\n\"" + getWord(lexicon.getComment()) + " " + getWord(lexicon.getComment()) + "\"");
		plea.append("\n\"These details present the case in a new light. I will investigate.\" ");
		plea.append("\nHe " + getWord(lexicon.getMovement()) + " toward the door and " + getWord(lexicon.getSpeak())
				+ ", \"Come Watson, we have several visits to make!\"");

		return plea;
	}

	public StringBuilder makeInquiries() {
		StringBuilder inquiries = new StringBuilder();

		if (this.inquiryCount < this.TOTAL_INFORMANTS) {
			if (this.inquiryCount == this.TOTAL_INFORMANTS - 1) {
				Person informant = informants.get(TOTAL_INFORMANTS - 1);
				Person person1 = suspects.get(r.nextInt(TOTAL_SUSPECTS));
				Person person2 = suspects.get(r.nextInt(TOTAL_SUSPECTS));

				inquiries.append("\n\"" + getWord(lexicon.getGreeting()) + ", " + informant.getFirst() + ". ");
				inquiries.append("I expect you know why we're here.\" " + informant.getFirst() + " "
						+ getWord(lexicon.getMovement()) + " at our entrance. ");
				inquiries.append("It is not your " + getWord(lexicon.getDistinction()) + ", charming though it is. ");
				inquiries.append(getWord(lexicon.getInterrogation()) + "\"");
				inquiries.append("\n\"I " + getWord(lexicon.getSense()) + " that " + person1.getFullName() + " "
						+ getWord(lexicon.getTransVerb()) + " a " + person1.getObject() + " from the "
						+ crime.getPlace() + ". And I says - what's a " + person1.getProfession() + " doing here? ");
				inquiries.append("But then, " + person2.getFullName() + " " + getWord(lexicon.getMovement())
						+ " from the " + crime.getPlace() + " and I " + getWord(lexicon.getSense()) + " some kind of "
						+ crime.getWeapon() + " nearby. ");
				inquiries.append(getWord(lexicon.getConfused()) + " Anyway I " + getWord(lexicon.getMovement()) + " away "
						+ getWord(lexicon.getUrgent()) + ".\" ");
				inquiries.append("\n\n\"Had you only stayed by a moment longer, we might have had our answer! "
						+ getWord(lexicon.getComment()) + " We must be along. " + getWord(lexicon.getGreeting())
						+ ". ");
				inquiries.append("Come Watson, we must speak with " + informants.get(TOTAL_INFORMANTS - 2).getFullName()
						+ " " + getWord(lexicon.getUrgent()) + ". ");
				inquiries.append("Good day, " + informant.getFirst() + ". ");
				this.inquiryCount += 1;
			} else if (this.inquiryCount == this.TOTAL_INFORMANTS - 2) {
				Person informant = informants.get(TOTAL_INFORMANTS - 2);
				Person person1 = suspects.get(r.nextInt(TOTAL_SUSPECTS));
				Person person2 = suspects.get(r.nextInt(TOTAL_SUSPECTS));

				inquiries.append("\n\"Look, Watson! " + informant.getFullName() + " is there, in the "
						+ getWord(lexicon.getPlace()) + ".\" ");
				inquiries.append("\n He  " + getWord(lexicon.getMovement()) + " toward " + informant.getFirst() + ", ");
				inquiries.append("\"" + informant.getFirst() + ", " + getWord(lexicon.getInterrogation()) + ".\"");
				inquiries.append("\n\"Mr. Holmes! What are you doing here?\" ");
				inquiries.append(
						"\n\"Investigating a " + crime.getCrime() + ". " + getWord(lexicon.getInterrogation()) + ".\"");
				inquiries.append("\n\"I know nothing!\"");
				inquiries.append("\n\"" + informant.getFirst() + ", it is commonly know that a skilled "
						+ informant.getProfession() + " such as yourself knows a great deal about " + crime.getCrime()
						+ ". ");
				inquiries.append("The " + informant.getProfession() + " guild keeps tabs on all the "
						+ victim.getProfession() + " business in " + crime.getCity() + ", including that of "
						+ victim.getFullName() + ". ");
				inquiries.append(getWord(lexicon.getInterrogation()) + "\"");
				inquiries.append("\n\n" + informant.getFirst() + " appeared to " + getWord(lexicon.getThink())
						+ " whether there was a hope of escaping our questions, then resigned. ");
				inquiries.append("\n\"Alright. It was " + person1.getFullName() + ". ");
				inquiries.append("That's who had the " + crime.getWeapon() + " last. ");
				inquiries.append(
						"I saw it all " + crime.getTime() + " in the " + crime.getPlace() + ". The " + crime.getWeapon()
								+ " was hidden in " + person2.getFullName() + "'s " + person2.getObject() + ". ");
				inquiries.append("Only " + person1.getFirst() + " would know about " + person2.getFirst() + "'s secret "
						+ person2.getObject() + ". Nothing else makes sense.\"");
				inquiries.append("\n\"" + getWord(lexicon.getUnimpressed())
						+ " Your data helps narrow the suspects, but your deductions are rash. ");
				inquiries.append("More facts are needed before we conjecture. ");
				inquiries.append("Come Watson, we must go to " + crime.getCity() + ".\"");
				this.inquiryCount += 1;
			} else if (this.inquiryCount == TOTAL_INFORMANTS - 3) {
				Person informant = informants.get(TOTAL_INFORMANTS - 3);
				Person person1 = suspects.get(r.nextInt(TOTAL_SUSPECTS));
				Person person2 = suspects.get(r.nextInt(TOTAL_SUSPECTS));

				inquiries.append("\n\"" + informant.getFullName() + ", " + getWord(lexicon.getInterrogation()) + "\"");
				inquiries.append("\n\"Holmes! How did you find me?\"");
				inquiries.append("\n\"I looked for your " + getWord(lexicon.getDistinction()) + " and your "
						+ getWord(lexicon.getAdjObject()) + " " + getWord(lexicon.getDistinction()) + ". "
						+ getWord(lexicon.getInterrogation()) + "\"");
				inquiries.append("Holmes smiled as I paused to find the exits and " + getWord(lexicon.getThink())
						+ " whether  " + informant.getFirst() + " was a " + getWord(lexicon.getAdjPerson())
						+ " sort of person. ");
				inquiries.append("\n\"It was " + person1.getFullName() + ". ");
				inquiries.append("That's who was in the " + crime.getPlace() + " last. ");
				inquiries.append("There's folks saying " + person2.getFullName() + " was the culprit, but a "
						+ person2.getProfession() + " couldn't have done it!\" " + informant.getFirst() + " "
						+ getWord(lexicon.getSpeak()));
				inquiries.append("\nLast night in the " + getWord(lexicon.getPlace()) + ", I saw the "
						+ crime.getWeapon() + " hidden in " + person1.getFirst() + "'s " + getWord(lexicon.getPlace())
						+ ". No one else could have got it before the " + crime.getCrime() + ". Only a "
						+ person1.getProfession() + " could get in and out before the coppers came.\" ");
				inquiries.append("\n\"You are certain?\" ");
				inquiries.append("\n\"" + getWord(lexicon.getReply()) + " I am certain that " + person2.getFirst()
						+ " " + getWord(lexicon.getSpeak()) + " that the " + crime.getWeapon() + " was "
						+ getWord(lexicon.getMovement()) + " " + getWord(lexicon.getPreposition()) + " the "
						+ crime.getPlace() + " right after it happened. It had to be " + person1.getFirst() + ". ");
				inquiries.append("\n\"" + getWord(lexicon.getComment()) + " " + getWord(lexicon.getComment()) + " ");
				inquiries.append("Thank you, " + informant.getFirst() + ". ");
				inquiries.append("We will travel to " + getWord(lexicon.getDestination()) + " "
						+ getWord(lexicon.getUrgent()) + ". \"");
				this.inquiryCount += 1;
			}
		} else {
			inquiries.append("\"" + suspects.get(r.nextInt(TOTAL_SUSPECTS)).getFullName() + ", "
					+ getWord(lexicon.getInterrogation()) + "\n\"");
			inquiries.append("\n\"" + getWord(lexicon.getUnimpressed()) + "\"");

		}
		return inquiries;
	}

	public StringBuilder getInvestigation() {
		Person lastInformant = suspects.get(r.nextInt(TOTAL_SUSPECTS));

		StringBuilder investigation = new StringBuilder();
		investigation.append("\n\"At last!\" " + "Holmes " + getWord(lexicon.getMovement()) + " from the "
				+ getWord(lexicon.getVehicle()) + ". ");
		investigation.append("\"First, we must look at the " + getWord(lexicon.getPlace()) + ".\"");
		investigation.append("\n\"What? " + victim.getFirst() + " had no business there.\"");
		investigation.append("\n\"Don't be sure - look at the " + getWord(crime.getClues()) + "! ");
		investigation.append("Come, let us continue to search the area!\"\n");

		investigation.append(this.getSearch());

		investigation.append("\n\n\"Watson - look! A " + getWord(crime.getClues()) + " "
				+ getWord(lexicon.getPreposition()) + " the " + getWord(lexicon.getPlace()) + "!\" ");
		investigation.append("\n\"" + getWord(lexicon.getConfused()) + "\"");
		investigation.append("\nWe " + getWord(lexicon.getSearch()) + " for more clues.\n");
		investigation.append("\n\"Holmes, over here - a " + getWord(lexicon.getObject()) + ". ");
		investigation.append("\n\"" + getWord(lexicon.getComment()) + "\"");
		investigation.append("\n\"" + getWord(lexicon.getComment()) + " Let's look " + getWord(lexicon.getPreposition())
				+ " the " + getWord(lexicon.getPlace()) + ". ");
		investigation.append("\n\"It seems " + victim.getFirst() + " " + getWord(lexicon.getMovement()) + " to find a "
				+ getWord(lexicon.getProfession()) + "  shortly before the " + crime.getCrime() + ".\" ");

		investigation.append(this.getSearch());

		investigation
				.append("\n\n\"Holmes, look! " + victim.getFirst() + "'s " + getWord(lexicon.getProfession()) + "!\"");
		investigation.append("\n\"" + lastInformant.getFullName() + "! " + lastInformant.getFirst() + "!\"");
		investigation.append(
				"\n\"Watson, " + lastInformant.getFirst() + " is " + getWord(lexicon.getMovement()) + " away. ");
		investigation.append("Blast! We must catch up! " + lastInformant.getFullName() + " has vital information!\"");
		investigation.append(makeInquiries());
		investigation.append("\n\"" + "This is coming together. " + getWord(lexicon.getComment()) + "\"");
		investigation.append("\n\"To Baker Street!\"");

		return investigation;
	}

	public StringBuilder getSearch() {
		int choice = r.nextInt(this.SEARCH_PATHS);
		StringBuilder search = new StringBuilder();
		switch (choice) {
		case 0:
			search = this.getSearch1();
			break;
		case 1:
			search = this.getSearch2();
			break;
		case 2:
			search = this.getSearch3();
			break;
		case 3:
			search = this.getSearch4();
			break;
		case 4:
			search = this.getSearch5();
			break;

		}

		return search;

	}

	public StringBuilder getSearch1() {
		StringBuilder search = new StringBuilder();
		search.append("\n\nWe " + getWord(lexicon.getSearch()) + " " + getWord(lexicon.getPreposition()) + " every "
				+ getWord(lexicon.getObject()) + " in the area. ");
		search.append("We turned up several " + getWord(lexicon.getAdjObject()) + " " + getWord(lexicon.getObject())
				+ "s and one " + getWord(lexicon.getAdjPerson()) + " " + getWord(lexicon.getObject()) + ". ");
		search.append(
				"Holmes took a keen interest in each item, but I saw no connection to the " + crime.getCrime() + ". ");
		search.append("He " + getWord(lexicon.getMovement()) + " away and " + getWord(lexicon.getSearch()) + " the "
				+ getWord(lexicon.getObject()) + " " + getWord(lexicon.getPreposition()) + " a nearby "
				+ getWord(lexicon.getPlace()) + ". ");
		search.append("I wondered about the " + getWatsonWonder() + ". ");
		search.append("I sighed and " + getWord(lexicon.getMovement()) + " after my companion. ");

		return search;

	}

	public StringBuilder getSearch2() {
		StringBuilder search = new StringBuilder();
		search.append("\n\nWe lifted the " + getWord(lexicon.getObject()) + " and found " + getWord(lexicon.getObject())
				+ ". ");
		search.append("\n\"" + getWord(lexicon.getComment()) + "! Watson - look under the "
				+ getWord(lexicon.getObject()) + ". ");
		search.append("I expect you will find " + this.victim + "'s " + getWord(lexicon.getObject()) + ".\" ");
		search.append("\nI dashed over. \"It is exactly as you say!\"");
		search.append("\n\"Go to Inn of the Prancing " + getWord(lexicon.getProfession())
				+ ". You must keep the police occupied there until my telegraph! I must investigate the "
				+ getWord(lexicon.getPlace()) + " alone!\"");
		search.append("\nI " + getWord(lexicon.getMovement()) + " to the inn, thinking about the " + getWatsonWonder());

		return search;

	}

	public StringBuilder getSearch3() {
		StringBuilder search = new StringBuilder();
		search.append("\n\nWe saw a " + getWord(lexicon.getAdjPerson()) + " person near the "
				+ getWord(lexicon.getPlace()) + ". ");
		search.append("\n\"Hallo! Who's that?\"");
		search.append("\nHolmes glanced up. \"It's a " + getWord(lexicon.getProfession()) + " looking for a "
				+ getWord(lexicon.getObject()) + ".\" ");
		search.append("\n\"Holmes this is ridiculous even for you - that figure must be 30 feet away!\"");
		search.append("\n\"Observe the " + getWord(lexicon.getDistinction())
				+ " - characteristics only amongst that profession. ");
		search.append("Besides, I observed that particular person making inquiries at the "
				+ getWord(lexicon.getPlace()) + " just before we left. ");
		search.append(
				"Really Watson, you must learn to stop looking and start observing. You will always find the answer. \"");
		search.append("\n\nThis was an admonishment I had heard many times before. I shook my head. ");
		search.append(
				"Somehow, I doubted I should Holmes that I had been thinking about the " + getWatsonWonder() + " ");
		search.append("I shook my head. No doubt Holmes had an answer to that question as well.");

		return search;

	}

	public StringBuilder getSearch4() {
		StringBuilder search = new StringBuilder();
		search.append("\n\nI " + getWord(lexicon.getSense()) + " a " + getWord(lexicon.getProfession()) + " nearby. ");
		search.append("Perhaps this would bring a clue?");
		search.append("\n\"" + getWord(lexicon.getGreeting()) + " - perhaps we might ask you a few questions?\"");
		search.append(
				"\n\"Watson, this person has only just returned from " + getWord(lexicon.getDestination()) + ". ");
		search.append("Observe his " + getWord(lexicon.getDistinction()) + " and the " + getWord(lexicon.getAdjObject())
				+ " " + getWord(lexicon.getObject()) + " in his bag. Inquiry will be useless.\" ");
		search.append("He " + getWord(lexicon.getMovement()) + " away disdainfully. ");
		search.append("\"Good day, sir. Come Watson, we must gather facts! To the " + getWord(lexicon.getPlace()));

		return search;
	}

	public StringBuilder getSearch5() {
		StringBuilder search = new StringBuilder();
		search.append("\n\n\"" + getWord(lexicon.getComment()) + ". A " + getWord(lexicon.getAdjObject()) + ", "
				+ getWord(lexicon.getColor()) + "! ");
		search.append("Come look Watson! We are hot on the trail!\"");
		search.append("\n\"" + getWord(lexicon.getGreeting()) + " - perhaps we might ask you a few questions?\"");
		search.append("\n\"Indeed Holmes! I tried to maintain my enthusiasm, this " + crime.getCrime()
				+ " investigation was trying my nerves. My thoughts were with the " + getWatsonWonder());
		search.append("\n\nI " + getWord(lexicon.getMovement()) + " " + getWord(lexicon.getPreposition()) + " the "
				+ getWord(lexicon.getPlace()) + " where Holmes had " + getWord(lexicon.getSearch()) + ". ");

		return search;
	}

	public StringBuilder getWatsonWonder() {
		int choice = r.nextInt(this.WATSON_WONDERS);
		StringBuilder wonder = new StringBuilder();
		if (choice == 0) {
			wonder.append(getWord(lexicon.getEvidence()) + " experiment I found in the apartment "
					+ getWord(lexicon.getTime()));
			wonder.append(" in our shared rooms. Would Mrs. Hudson have time to clean it up before we returned? ");
		} else if (choice == 1) {
			wonder.append(crime.getWeapon() + " and the " + crime.getPlace() + ". What could have happened? ");
		} else if (choice == 2) {
			wonder.append(getWord(lexicon.getProfession()) + " I hired " + getWord(lexicon.getTime())
					+ ". Would he resolved the trouble with my " + getWord(lexicon.getObject()) + ". ");
		} else if (choice == 3) {
			wonder.append(getWord(lexicon.getAdjPerson()) + ", " + getWord(lexicon.getAdjPerson()) + " "
					+ informants.get(2).getFullName() + ". Holmes had said there was a new "
					+ getWord(lexicon.getProfession()) + " in " + informants.get(2).getFirst() + "'s house who had a "
					+ getWord(lexicon.getVehicle()) + ". How the devil did he do that? ");
		} else if (choice == 4) {
			wonder.append(getWord(lexicon.getObject()) + " " + getWord(lexicon.getObject()) + " found at the crime. "
					+ "How did this fit with the " + crime.getCrime() + "? ");
		} else if (choice == 5) {
			wonder.append(getWord(lexicon.getEvidence()) + " Holmes had left in the icebox. Perhaps it would be "
					+ "prudent to eat out this evening. But where? And how could I sneak away without alerting Holmes? ");
		} else if (choice == 6) {
			wonder.append(getWord(lexicon.getColor()) + " " + getWord(lexicon.getObject())
					+ " my fiancee had purchased for our wedding. ");
			wonder.append(
					"I hoped her taste wasn't always so extravagent. What on earth would we do with it after the wedding? ");
		} else if (choice == 7) {
			wonder.append(getWord(lexicon.getAdjPerson()) + ", " + getWord(lexicon.getAdjPerson())
					+ " housekeeper my fiancee had recently hired. ");
			wonder.append("Would she force me to discard my " + getWord(lexicon.getObject()) + " collection? ");
		} else if (choice == 8) {
			wonder.append(crime.getCrime() + ". What a " + getWord(lexicon.getAdjObject())
					+ " happening! How would we solve it? ");
		} else {
			wonder.append(getWord(lexicon.getCrime()) + " Holmes solved last year. Would we get so lucky again? ");
		}

		return wonder;
	}

	public StringBuilder getResolution() {
		StringBuilder resolution = new StringBuilder();
		resolution.append("\n\"Lestrade, thank you for joining us. This will be of interest to the Yard.\"");
		resolution.append("\nLestrade " + getWord(lexicon.getMovement()) + " in, along with two constables. ");
		resolution.append("They were shortly joined by " + culprit.getFullName() + ", "
				+ informants.get(r.nextInt(TOTAL_INFORMANTS)).getFullName() + ", and " + client.getFullName() + ". ");
		resolution.append ("Mrs. Hudson brought in tea and laid it out, ignoring the " + getWord(lexicon.getEvidence())
				+ " experiment that Holmes had assembled since we'd arrived back. I wondered about the "
				+ getWatsonWonder());
		resolution.append("Holmes' voice brought me back to the meeting at hand. ");
		resolution.append("\n\n\"We must examine the facts: ");
		resolution.append("\n\"We ");
		int count = 0;
		while (count < this.TOTAL_INFORMANTS) {
			resolution.append("learned " + suspects.get(count).getFullName() + " had a "
					+ suspects.get(count).getObject() + " " + getWord(lexicon.getConjunction()) + " "
					+ suspects.get(count + 1).getFullName() + " had a " + suspects.get(count + 1).getObject() + " "
					+ getWord(lexicon.getConjunction()) + " ");
			count += 2;
		}
		resolution.append("\n\"However, " + victim.getFullName() + " " + getWord(lexicon.getMovement()) + " to the "
				+ crime.getPlace() + " " + crime.getTime() + ". ");
		resolution.append("This means that " + culprit.getFullName() + " " + getWord(lexicon.getTransVerb()) + " the "
				+ crime.getWeapon() + ". ");
		resolution.append("But then " + culprit.getFirst() + " " + getWord(lexicon.getSense()) + " " + victim.getFirst()
				+ " in the " + crime.getPlace() + ". ");
		resolution.append("\n\"From there, the " + crime.getCrime() + " was inevitable.\"");
		resolution.append("\n\"Are you saying that " + culprit.getFullName() + " is responsible?\" Lestrade sounded skeptical");
		resolution.append("\n\"Precisely. The " + getWord(crime.getClues()) + " found next to the " + crime.getPlace()
				+ " makes it certain.\"");
		resolution.append("\n\nI glanced around the room, " + culprit.getFirst() + "'s eyes " + getWord(lexicon.getSearch())
				+ " for an escape. ");
		resolution
				.append("Holmes had anticipated this and positioned the constables at each exit. The constables looked like members of a "
						+ getWord(lexicon.getAdjPerson()) + " " + getWord(lexicon.getObject()) + " army. ");
		resolution.append("\n\"Make the arrest, Lestrade!\"");

		return resolution;
	}

	public StringBuilder getEnding() {
		StringBuilder ending = new StringBuilder();
		ending.append("\n\nOver breakfast the next morning, I was still marveling at Holmes' brilliant deductions. ");
		ending.append("\"Holmes, " + getWord(lexicon.getConfused()) + " how could you possibly discover " + culprit.getFullName()
				+ "'s guilt?\"");
		ending.append("\n\"" + culprit.getFullName() + "'s guilt was the only possible solution fitting the facts.\"");
		ending.append("\n\"Recall the testimony of " + informants.get(r.nextInt(TOTAL_INFORMANTS)).getFullName() + ".\"");
		ending.append("\n\"Their report fit with others of " + culprit.getFirst() + " having a " + culprit.getObject()
				+ " just before the " + crime.getCrime() + ", meaning only " + culprit.getFirst() + " could have been "
				+ getWord(lexicon.getPreposition()) + " the " + crime.getPlace() + " at the time of the "
				+ crime.getCrime() + " with the " + crime.getWeapon() + ".\"");
		ending.append("\n\"It seems obvious now!\"");
		ending.append("\n\"Deduction, Watson. Crime can only be solved by careful deduction.\" ");
		ending.append("\n\"Now that the investigation is over, I think it's time I continue experimenting on the "
				+ getWord(lexicon.getEvidence()) + " " + getWord(lexicon.getPreposition()) + " our "
				+ getWord(lexicon.getPlace()) + ". I do hope Mrs. Hudson left it alone while we were away.\" ");

		return ending;
	}

	public String getWord(ArrayList<String> words) {
		return words.get(r.nextInt(words.size() - 1));
	}

	public Person getPerson() {
		String first = getWord(lexicon.getFirst());
		String last = getWord(lexicon.getLast());
		String profession = getWord(lexicon.getProfession());
		String object = getWord(lexicon.getObject());
		Person person = new Person(first, last, profession, object);

		return person;
	}

	public void getPeople(int size, ArrayList<Person> people) {
		for (int i = 0; i < size; i++) {
			people.add(getPerson());
		}
	}

	public CrimeScene getCrimeScene() {
		String thisCrime = getWord(lexicon.getCrime());
		String place = getWord(lexicon.getPlace());
		String city = getWord(lexicon.getDestination());
		String time = getWord(lexicon.getTime());
		String weapon = this.culprit.getObject();

		ArrayList<String> clues = new ArrayList<String>();

		// adds object clues to the crime scene
		for (Person p : suspects) {
			clues.add(p.getObject());
		}

		// adds number evidence clues to the crime scene proportional to ##
		// informants
		for (int i = 0; i < TOTAL_INFORMANTS; i++) {
			clues.add(getWord(lexicon.getEvidence()));
		}

		CrimeScene crime = new CrimeScene(thisCrime, place, city, time, weapon, clues);

		return crime;

	}

}
