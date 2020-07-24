import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import CategoryService from './category.service';
import Category from './data/entities/category.entity';
import QuestionType from './data/entities/question_type.entity';
import GeneralController from './general.controller';
import QuestionTypeService from './option_type.service';

@Module({
  providers: [CategoryService, QuestionTypeService],
  controllers: [GeneralController],
  imports: [TypeOrmModule.forFeature([Category, QuestionType])],
})
export class GeneralModule {}
