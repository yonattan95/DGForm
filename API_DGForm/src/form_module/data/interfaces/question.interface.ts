export interface NewQuestionI {
  quizId: number;
  questionTypeId: number;

  name: string;
  description: string;
  numberQuestion: number;
}
export interface QuestionI {
  questionId: number;
  quizId: number;
  questionTypeId: number;

  name: string;
  description: string;
  numberQuestion: number;
}
