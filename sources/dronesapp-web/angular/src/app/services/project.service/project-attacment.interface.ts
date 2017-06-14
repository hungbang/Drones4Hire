export interface ProjectAttachmentModel {
  attachmentURL: string;
  createdAt?: string;
  id?: number;
  modifiedAt?: string;
  projectId: number;
  title: string;
  titleValid?: boolean;
  type: string
}
