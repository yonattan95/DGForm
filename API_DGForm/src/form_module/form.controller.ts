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
import NewFormI from './data/interfaces/form.interface';

@Controller('forms')
export class FormController {
  constructor(private readonly formService: FormService) {}

  @Get('pending')
  async getPendingFormList(): Promise<ResponseAPI<Array<Form>>> {
    const list = await this.formService.getPendingFormList();
    return new SuccessResponse(list);
  }
  @Get('complete')
  async getCompleteFormList(): Promise<ResponseAPI<Array<Form>>> {
    const list = await this.formService.getCompleteFormList();
    return new SuccessResponse(list);
  }
  @Get()
  async getFormList(): Promise<ResponseAPI<Array<Form>>> {
    const list = await this.formService.getAllForm();
    return new SuccessResponse(list);
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
}
