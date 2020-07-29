import {
  Controller,
  Get,
  Post,
  Req,
  Body,
  Param,
} from '@nestjs/common';
import { FormService } from './form.service';
import {
  ResponseAPI,
  SuccessResponse,
  FailResponse,
} from '../common/dto/response.dto';
import { Form } from './data/entities/form.entity';
import {
  AssignedIntervierwerToFormI,
  NewFormI,
} from './data/interfaces/form.interface';
import ErrorResponseException from 'src/common/exceptions/error_response.exception';
import { NewAnswerI } from './data/interfaces/answer.interface';
import QuizService from './quiz.service';

@Controller('forms')
export class FormController {
  constructor(
    private readonly formService: FormService,
    private readonly quizService: QuizService,
  ) {}

  @Get('pending')
  async getPendingFormList() {
    const list = await this.formService.getPendingFormList();
    return new SuccessResponse(list);
    // return new SuccessResponse({
    //   pendingFormList: list,
    //   totalPendingFormList: list.length,
    // });
  }
  @Get('complete')
  async getCompleteFormList() {
    const list = await this.formService.getCompleteFormList();
    return new SuccessResponse(list);
    // return new SuccessResponse({
    //   completeFormList: list,
    //   totalCompleteFormList: list.length,
    // });
  }
  @Get()
  async getFormList() {
    const list = await this.formService.getAllForm();
    return new SuccessResponse(list);
    // return new SuccessResponse({
    //   formList: list,
    //   totalFormList: list.length,
    // });
  }
  @Get(':id')
  async getFormById(
    @Param('id') id: number,
  ): Promise<ResponseAPI<Form>> {
    const form = await this.formService.getFormById(id);
    return form
      ? new SuccessResponse(form)
      : new FailResponse('El formulario no existe.');
  }
  @Post()
  async newForm(
    @Body() body: NewFormI,
  ): Promise<ResponseAPI<String>> {
    await this.formService.saveForm(body);
    return new SuccessResponse('Se creo el formulario');
  }

  @Post('interviewer')
  async assignedInterviewerToForm(
    @Body() data: AssignedIntervierwerToFormI,
  ) {
    const res = await this.formService.assingInterviewerToForm(data);
    if (!res)
      throw new ErrorResponseException({
        errorMessage:
          'Ocurrio un error al interntar asignar a un encuestador al formulario selecionado',
      });
    return new SuccessResponse('se asigno correctamente');
  }

  @Post('answer')
  async saveAnswer(@Body() newAnswer: NewAnswerI) {
    const answer = await this.quizService.saveAnswer(
      newAnswer.question,
      newAnswer.quiz,
      newAnswer.value,
    );
    console.log(answer);
    
    const question = await this.quizService.getQuestion(
      newAnswer.question,
    );
    console.log(question);
    const updateQuiz = await this.quizService.updateQuiz(
      answer.quiz,
      question.questionNumber,
    );
    console.log(updateQuiz);
    
    return new SuccessResponse({ answerId: answer.id });
  }

  @Get('question/:questionId')
  async getQuestion(@Param('questionId') questionId: number) {
    const question = await this.quizService.getQuestion(questionId);
    const answer = await this.quizService.getAnswer(question.id);
    return new SuccessResponse({ ...question, answer });
  }

  @Get('answer/:questionId')
  async getAnswer(@Param('questionId') questionId: number) {
    const answer = await this.quizService.getAnswer(questionId);
    return new SuccessResponse(answer);
  }
}
