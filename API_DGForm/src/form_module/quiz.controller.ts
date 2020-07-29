import { Body, Controller, Get, Param, Post } from '@nestjs/common';
import { SuccessResponse } from 'src/common/dto/response.dto';
import ErrorResponseException from 'src/common/exceptions/error_response.exception';
import { NewQuestionI } from './data/interfaces/question.interface';
import QuizService from './quiz.service';

@Controller('form/:formId')
export default class QuestionController {
  constructor(private readonly quizService: QuizService) {}

  @Post('questions')
  async newQuestion(
    @Body() question: NewQuestionI,
    @Param('formId') formId: number,
  ) {
    const newQuestion: NewQuestionI = { ...question, form: formId };
    const res = await this.quizService.createQuestion(newQuestion);
    if (!res)
      throw new ErrorResponseException({
        errorMessage: 'No se pudo crear la pregunta',
      });
    return new SuccessResponse('Pregunta creada');
  }

  @Get('interviewer/:interviewerId/questions')
  async getQuestionList(
    @Param('formId') formId: number,
    @Param('interviewerId') interviewerId: number,
  ) {
    const res = await this.quizService.getQuestionList(
      formId,
      interviewerId,
    );
    if (!res)
      throw new ErrorResponseException({
        errorMessage: 'ocurrio un problema',
      });
    return new SuccessResponse(res);
  }

  //verifica si el formulario del encuestador tiene un quiz con una pregunta incompleta
}
