import {
  Controller,
  Get,
  Post,
  Req,
  Body,
  Param,
  Put,
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
import { NewQuestionOptionI } from './data/interfaces/question_option.interface';

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
    // console.log(answer);
    if (answer === undefined)
      throw new ErrorResponseException({
        errorMessage: 'Esta pregunta ya cuenta con una respuesta.',
      });

    const question = await this.quizService.getQuestion(
      newAnswer.question,
    );
    // console.log(question);
    const updateQuiz = await this.quizService.updateQuiz(
      answer.quiz,
      question.questionNumber,
    );
    // console.log(updateQuiz);

    return new SuccessResponse({ answerId: answer.id });
  }

  @Get('question/:questionId')
  async getQuestion(@Param('questionId') questionId: number) {
    const question = await this.quizService.getQuestion(questionId);
    const optionList = await this.quizService.getQuestionOption(
      questionId,
    );
    return new SuccessResponse({ ...question, optionList });
  }

  @Get('quiz/:quizId/answer/:questionId')
  async getAnswer(
    @Param('quizId') quizId: number,
    @Param('questionId') questionId: number,
  ) {
    const answer = await this.quizService.getAnswer(
      questionId,
      quizId,
    );
    return new SuccessResponse(answer);
  }
  @Put('answer/:answerId')
  async updateAnswer(
    @Param('answerId') answerId: number,
    @Body('value') value: string,
  ) {
    const answer = await this.quizService.updateAnswer(
      answerId,
      value,
    );
    return new SuccessResponse(answer);
  }
  @Get('question/:questionId/options')
  async getQuestionOption(@Param('questionId') questionId: number) {
    const optionList = await this.quizService.getQuestionOption(
      questionId,
    );
    return new SuccessResponse(optionList);
  }

  @Post('question_options')
  async newQuestionOption(
    @Body() questionOption: NewQuestionOptionI,
  ) {
    const res = await this.quizService.createQuestionOption(
      questionOption,
    );

    if (!res)
      throw new ErrorResponseException({
        errorMessage: 'Ocurrio un problema al crear la optcion',
      });
    return new SuccessResponse('Se creo correctamente la opcion');
  }
}
