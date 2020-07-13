import {
  Body,
  Controller,
  Get,
  Param,
  Post,
  Put,
  UseGuards,
} from '@nestjs/common';
import JwtAuthGuard from 'src/auth_module/guards/jwt_auth.guard';
import {
  ResponseAPI,
  SuccessResponse,
} from 'src/common/dto/response.dto';
import {
  NewInterviewerRequest,
  InterviewerProfile,
} from './data/dto/interviewer.dto';
import {
  InterviewerI,
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
    @Body() interviewer: NewInterviewerRequest,
  ): Promise<ResponseAPI<string>> {
    await this.interviewerService.newInterviewer(interviewer);
    return new SuccessResponse('Encuestador creado');
  }

  @Put('state/:id')
  async updateState(
    @Param('id') id: number,
    @Body('state') state: number,
  ): Promise<ResponseAPI<string>> {
    await this.interviewerService.changeState({ id, state });
    return new SuccessResponse('Se cambio el estado con exito');
  }
  @Put('profile/:id')
  async updateProfile(
    @Param('id') id: number,
    @Body() data: UpdateInterviewerI,
  ): Promise<ResponseAPI<string>> {
    await this.interviewerService.updateInterviewerData(id, data);
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
    const profile = new InterviewerProfile({
      username: interviewer.username,
      email: interviewer.email,
      state: interviewer.state,
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
    return new SuccessResponse(interviewer);
  }
}
