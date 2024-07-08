word = input()
string = list(word)

num = 0
for i in range(0, len(string)):
    if (string[i].isupper()):
        num += 1


if (num > len(string) - num):
    print(word.upper())
else:
    print(word.lower())

