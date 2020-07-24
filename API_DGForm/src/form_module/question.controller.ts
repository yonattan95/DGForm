import { Body, Controller, Post } from '@nestjs/common';
import { SuccessResponse } from 'src/common/dto/response.dto';
import { NewQuestionI } from './data/interfaces/question.interface';
import QuestionService from './question.service';

@Controller('form/:id/questions')
export default class QuestionController {
  constructor(private readonly questionService: QuestionService) {}

  @Post()
  async newQuestion(@Body() question: NewQuestionI) {
    const res = await this.questionService.createQuestion(question);
    return new SuccessResponse('Pregunta creada');
  }
}
