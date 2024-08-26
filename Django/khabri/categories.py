from enum import Enum

class NewsCategories(Enum):
    BUSINESS = ("business", "Business")
    ENTERTAINMENT = ("entertainment", "Entertainment")
    HEALTH = ("health", "Health")
    SCIENCE = ("science", "Science")
    SPORTS = ("sports", "Sports")
    TECHNOLOGY = ("technology", "Technology")

    def _init_(self, value, display_name):
        self.value = value
        self.display_name=display_name