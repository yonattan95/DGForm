import { Form } from '../entities/form.entity';

export interface QuizI {
  id: number;
  form: Form;

  state: number;

  interviewer: number;

  lastQuestionNumberCompleted: number;
}
export interface NewQuizI {
  form: number;
  interviewer: number;
}
