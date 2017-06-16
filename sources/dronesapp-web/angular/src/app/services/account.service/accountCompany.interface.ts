export interface AccountCompanyModel {
  contactEmail: string;
  contactName: string;
  country: {
    id: number;
    name: string
  };
  state?: {
    code: string,
    id: number,
    name: string
  };
  id: number;
  name: string;
  webURL: string;
}
