import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import Question from './data/entities/question.entity';
import { NewQuestionI } from './data/interfaces/question.interface';

@Injectable()
export default class QuestionService {
  constructor(
    @InjectRepository(Question)
    private questionRepository: Repository<Question>,
  ) {}

  async createQuestion(question: NewQuestionI) {
    const res = await this.questionRepository.save(question);
    return res ? true : false;
  }
}
