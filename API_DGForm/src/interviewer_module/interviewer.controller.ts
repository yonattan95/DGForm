import {
  Body,
  Controller,
  Get,
  Param,
  Post,
  Put,
  UseGuards,
} from '@nestjs/common';
import JwtAuthGuard from 'src/auth_interviewer_module/guards/jwt_auth.guard';
import {
  ResponseAPI,
  SuccessResponse,
} from 'src/common/dto/response.dto';
import ErrorResponseException from 'src/common/exceptions/error_response.exception';
import {
  NewInterviewerRequest,
  InterviewerProfile,
} from './data/dto/interviewer.dto';
import {
  InterviewerI,
  NewInterviewerI,
  UpdateInterviewerI,
} from './data/interfaces/interviewer.interface';
import InterviewerService from './interviewer.service';

@Controller('interviewers')
export default class InterviewerController {
  constructor(
    private readonly interviewerService: InterviewerService,
  ) {}

  @Post()
  async newInterviewer(
    @Body() interviewer: NewInterviewerI,
  ): Promise<ResponseAPI<string>> {
    const res = await this.interviewerService.newInterviewer(
      interviewer,
    );
    if (!res)
      throw new ErrorResponseException({
        errorMessage: 'No se pudo crear el encuestador',
      });
    return new SuccessResponse('Encuestador creado');
  }

  @Put('state/:id')
  async updateState(
    @Param('id') id: number,
    @Body('state') state: number,
  ): Promise<ResponseAPI<string> | ErrorResponseException> {
    const res = await this.interviewerService.changeState({
      id,
      state,
    });
    console.log(res);

    if (!res) {
      throw new ErrorResponseException({
        errorMessage:
          'No se pudo actualizar el estado del encuestador',
      });
    }
    return new SuccessResponse('Se cambio el estado con exito');
  }
  @Put('profile/:id')
  async updateProfile(
    @Param('id') id: number,
    @Body() data: UpdateInterviewerI,
  ): Promise<ResponseAPI<string>> {
    const res = await this.interviewerService.updateInterviewerData(
      id,
      data,
    );
    if (!res)
      throw new ErrorResponseException({
        errorMessage:
          'No se pudo actualizar los datos del encuestador',
      });
    return new SuccessResponse('Se actualizo el perfil con exito');
  }

  // @UseGuards(JwtAuthGuard)
  @Get('profile/:id')
  async getProfile(
    @Param('id') id: number,
  ): Promise<ResponseAPI<InterviewerProfile>> {
    const interviewer = await this.interviewerService.getinterviewerById(
      id,
    );
    if (!interviewer)
      throw new ErrorResponseException({
        errorMessage: 'No existe un encuestador con ese ID',
      });
    const profile = new InterviewerProfile({
      name: interviewer.name,
      surname1: interviewer.surname1,
      surname2: interviewer.surname2,
      username: interviewer.username,
      email: interviewer.email,
      state: interviewer.state,
      image: interviewer.image,
    });
    return new SuccessResponse(profile);
  }
  @Get(':id')
  async getInterviewer(
    @Param('id') id: number,
  ): Promise<ResponseAPI<InterviewerI>> {
    const interviewer = await this.interviewerService.getinterviewerById(
      id,
    );
    if (!interviewer)
      throw new ErrorResponseException({
        errorMessage: 'No existe un encuestador con ese ID',
      });
    return new SuccessResponse(interviewer);
  }

  @Get()
  async getAllInterviewer() {
    const list = await this.interviewerService.getAllInterviewer();
    if (!list)
      throw new ErrorResponseException({
        errorMessage: 'No se pudo cargar la lista',
      });
    return new SuccessResponse(list);
  }
}
