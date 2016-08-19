-------------------------------------------------------------------------------------------

Code Points = Mapping from Integer to characters, mostly used in context with Unicode

Unicode -> 17 × 65.536 = 1.114.112 Code Points (https://en.wikipedia.org/wiki/List_of_Unicode_characters),

Why Unicode?

Not enough characters.
Incompatibility when interpreting the same bytes with different tables.
Documents might contain multiple character sets, e.g. English and Chinese.

https://en.wikipedia.org/wiki/List_of_Unicode_characters

-------------------------------------------------------------------------------------------

Character Encoding: Mapping from Code Points to a binary representation (Code Unit)

- Variable length encodings:
  - UTF-8 -> one to four octets (bytes), backwards compatible with ASCII
  - UTF-16 -> 2 or 4 bytes, often used as internal representation e.g. in Java

- Fixed length encodings:
  - ASCII -> 7-bits -> 128 Code Points (0-127) http://www.asciitable.com/
  - ISO 8859-1 (Superset of ASCII) -> 8-bits -> 256 Code Points
  - Windows-1252 -> 8-bits -> Similar to ISO 8859-1 except for Code Points 128 to 159