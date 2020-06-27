import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Form } from './data/entities/form.entity';
import { Repository } from 'typeorm';
import { FormResponse, FormRequest } from './data/dto/form.dto';

@Injectable()
export class FormService {
  constructor(
    @InjectRepository(Form) private formRespository: Repository<Form>,
  ) {}
  async saveForm(form: Form): Promise<boolean> {
    const newform = new Form();
    newform.name = form.name;
    newform.description = form.description;
    newform.state = form.state;
    await this.formRespository.save(form);
    return true;
  }
  getPendingFormList(): Promise<Array<Form>> {
    return this.formRespository.find({
      state: 0,
    });
  }
  getCompleteFormList(): Promise<Array<Form>> {
    return this.formRespository.find({
      state: 1,
    });
  }
  getFormById(id: number): Promise<Form> {
    return this.formRespository.findOne({
      id,
    });
  }
}
