import { Module } from '@nestjs/common';
import { FormController } from './form.controller';
import { FormService } from './form.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Form } from './data/entities/form.entity';
import QuestionController from './quiz.controller';
import QuestionService from './quiz.service';
import Question from './data/entities/question.entity';
import Quiz from './data/entities/quiz.entity';
import Answer from './data/entities/answer.entity';
import QuestionType from 'src/general_module/data/entities/question_type.entity';
import InterviewerToForm from './data/entities/interviewer_to_form.entity';
import QuestionOption from './data/entities/question_option.entity';
@Module({
  controllers: [FormController, QuestionController],
  providers: [FormService, QuestionService],
  imports: [
    TypeOrmModule.forFeature([
      Form,
      Question,
      Quiz,
      Answer,
      QuestionOption,
      InterviewerToForm,
    ]),
  ],
})
export class FormModule {}
