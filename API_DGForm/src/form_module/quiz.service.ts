import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import Answer from './data/entities/answer.entity';
import { Form } from './data/entities/form.entity';
import InterviewerToForm from './data/entities/interviewer_to_form.entity';
import Question from './data/entities/question.entity';
import QuestionOption from './data/entities/question_option.entity';
import Quiz from './data/entities/quiz.entity';
import { NewQuestionI } from './data/interfaces/question.interface';
import { NewQuizI } from './data/interfaces/quiz.interface';

@Injectable()
export default class QuizService {
  constructor(
    @InjectRepository(Question)
    private questionRepository: Repository<Question>,
    @InjectRepository(Answer)
    private answerRepository: Repository<Answer>,

    @InjectRepository(Quiz)
    private quizRepository: Repository<Quiz>,

    @InjectRepository(QuestionOption)
    private questionOptionRepository: Repository<QuestionOption>,
    @InjectRepository(InterviewerToForm)
    private interviewerToFormRepository: Repository<
      InterviewerToForm
    >,
  ) {}

  async createQuestion(question: NewQuestionI) {
    const res = await this.questionRepository.save(question);
    return res ? true : false;
  }

  //creacion de la encuestra
  async createQuiz(form: number) {
    const quiz: NewQuizI = { form };
    return this.quizRepository.save(quiz);
    // return res ? true : false;
  }

  //Listado de preguntas por formulario
  async getQuestionList(formId: number, interviewerId: number) {
    return this.questionRepository.find({
      where: { form: formId },
      relations: ['form', 'questionType'],
    });
  }
  async getQuestion(questionId: number) {
    return this.questionRepository.findOne({
      where: { id: questionId },
      relations: ['form', 'questionType'],
    });
  }
  async getAnswer(questionId: number) {
    return this.answerRepository.findOne({
      where: { question: questionId },
      relations: ['question', 'quiz'],
    });
  }

  //Trata de encontrar alguna encuesta incompleta
  async getLastQuiz(
    interviewerId: number,
    formId: number,
  ): Promise<Quiz | undefined> {
    const quiz = await this.quizRepository
      .createQueryBuilder('quiz')
      .where(qb => {
        const subQuery = qb
          .subQuery()
          .select('interviewerToForm.form')
          .from(InterviewerToForm, 'interviewerToForm')
          .where('interviewerToForm.interviewer= :interviewerId', {
            interviewerId,
          })
          .andWhere('interviewerToForm.form = :formId', { formId })
          .getQuery();
        return 'quiz.form IN ' + subQuery;
      })
      .andWhere('quiz.state = :state', { state: 0 })
      .orderBy("quiz.id","DESC")
      .getOne();

    console.log(quiz);
    return quiz;
  }

  async saveAnswer(
    questionId: number,
    quizId: number,
    value: string,
  ) {
    const answer = new Answer();
    answer.question = questionId;
    answer.quiz = quizId;
    answer.value = value;
    return await this.answerRepository.save(answer);
  }

  async updateQuiz(
    quizId: number,
    lastQuestionNumberCompleted: number,
  ) {
    console.log(quizId);
    console.log(lastQuestionNumberCompleted);
    
    return await this.quizRepository.update(quizId, {
      lastQuestionNumberCompleted,
    });
  }

  async getQuizList(formId:number,interviewerId:number){
    return this.quizRepository.find()
  }
}
