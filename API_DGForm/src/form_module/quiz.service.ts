import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import Answer from './data/entities/answer.entity';
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
  ) {}

  async createQuestion(question: NewQuestionI) {
    const res = await this.questionRepository.save(question);
    return res ? true : false;
  }

  //creacion de la encuestra
  async createQuiz(form: number) {
    const quiz: NewQuizI = { form };
    const res = this.quizRepository.save(quiz);
    return res ? true : false;
  }

  //Listado de preguntas por formulario
  async getQuestionList(formId: number, interviewerId: number) {
    return this.questionRepository.find({
      where: { form: formId },
      relations: ['form', 'questionType'],
    });
  }

  //Trata de encontrar alguna encuesta incompleta
  async getLastQuiz() {}
}
