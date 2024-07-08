S = input()
words = [S]
for i in range(len(S)):
    words.append(S[i:] + S[:i])
words = sorted(words)
print(words[0])
print(words[-1])