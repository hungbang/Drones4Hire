export interface ServiceModel {
  id: number;
  name: string;
  checked?: boolean;
  category: {
    id: number;
    name: string;
    order: number;
  }
}

export interface NormalizedServiceModel {
  id: number;
  name: string;
  order: number;
  category: NormalizedServiceCategoryModel[];
}

interface NormalizedServiceCategoryModel {
  id: number;
  name: string;
  checked?: boolean;
}
