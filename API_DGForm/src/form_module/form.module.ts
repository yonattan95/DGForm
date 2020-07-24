import { Module } from '@nestjs/common';
import { FormController } from './form.controller';
import { FormService } from './form.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Form } from './data/entities/form.entity';
import QuestionController from './question.controller';
import QuestionService from './question.service';
import Question from './data/entities/question.entity';
@Module({
  controllers: [FormController, QuestionController],
  providers: [FormService, QuestionService],
  imports: [TypeOrmModule.forFeature([Form, Question])],
})
export class FormModule {}
