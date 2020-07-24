import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import QuestionType from './data/entities/question_type.entity';

@Injectable()
export default class CategoryService {
  constructor(
    @InjectRepository(QuestionType)
    private categoryRepository: Repository<QuestionType>,
  ) {}

  async getAll() {
    return this.categoryRepository.find();
  }
}
