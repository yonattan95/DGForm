export interface NewQuestionI {
  name: string;
  description: string;
  questionNumber: number;

  form: number;
  questionType: number;
}
export interface QuestionI {
  id: number;
  name: string;
  description: string;
  questionNumber: number;

  questionType: number;
  form: number;
}
