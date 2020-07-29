import { Form } from '../entities/form.entity';

export interface QuizI {
  id: number;
  form: Form;

  state: number;

  lastQuestionNumberCompleted: number;
}
export interface NewQuizI {
  form: number;
}
