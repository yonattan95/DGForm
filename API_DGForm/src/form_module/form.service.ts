import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Form } from './data/entities/form.entity';
import { Repository } from 'typeorm';
import { FormResponse, FormRequest } from './data/dto/form.dto';
import {
  AssignedIntervierwerToFormI,
  NewFormI,
} from './data/interfaces/form.interface';
import { Interviewer } from 'src/interviewer_module/data/entities/interviewer.entity';
import InterviewerToForm from './data/entities/interviewer_to_form.entity';

@Injectable()
export class FormService {
  constructor(
    @InjectRepository(Form) private formRepository: Repository<Form>,
    @InjectRepository(InterviewerToForm)
    private interviewerToFormRepository: Repository<
      InterviewerToForm
    >,
  ) {}
  async saveForm(form: NewFormI): Promise<Form> {
    const res = await this.formRepository.save(form);
    return res;
  }
  // async updateForm(form: UpdateFor): Promise<Form> {
  //   const res = await this.formRepository.save(form);
  //   return res;
  // }
  async getPendingFormList(): Promise<Array<Form>> {
    return this.formRepository.find({
      where: {
        state: 0,
      },
      relations: ['category', 'user'],
    });
  }
  async getCompleteFormList(): Promise<Array<Form>> {
    return this.formRepository.find({
      where: {
        state: 1,
      },
      relations: ['category', 'user'],
    });
  }
  async getPendingFormListByInterviewer(
    interviewerId: number,
  ): Promise<any> {
    const list = await this.interviewerToFormRepository
      .createQueryBuilder('interviewerToForm')
      .leftJoinAndSelect('interviewerToForm.form', 'form')
      .where('interviewerToForm.interviewer = :interviewerId', {
        interviewerId,
      })
      .andWhere('form.state = :state', { state: 0 })
      .getMany();
    return list;
  }
  async getCompleteFormListByInterviewer(
    interviewerId: number,
  ): Promise<any> {
    const list = await this.interviewerToFormRepository
      .createQueryBuilder('interviewerToForm')
      .leftJoinAndSelect('interviewerToForm.form', 'form')
      .where('interviewerToForm.interviewer = :interviewerId', {
        interviewerId,
      })
      .andWhere('form.state = :state', { state: 1 })
      .getMany();
    return list;
  }
  async getFormById(id: number): Promise<Form> {
    return this.formRepository.findOne({
      where: {
        id,
      },
      relations: ['category', 'user'],
    });
  }
  async getFormListByUser(userId: number) {
    return this.formRepository.find({
      where: {
        user: userId,
      },
      relations: ['category'],
    });
  }

  async getAllForm() {
    return this.formRepository.find({
      relations: ['user', 'category'],
    });
  }

  async assingInterviewerToForm(data: AssignedIntervierwerToFormI) {
    const res = await this.interviewerToFormRepository.insert(data);
    console.log(res);
    return res ? true : false;
  }
}
