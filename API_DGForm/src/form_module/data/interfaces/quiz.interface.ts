export interface QuizI {
  id: number;
  form: number;

  state: number;

  lastQuestionCompleted: number;
}
export interface NewQuizI {
  form: number;
}
