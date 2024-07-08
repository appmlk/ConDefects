s = list(input())

S = sorted(list(set(s)))

str = ""

for i in range(len(S)-1):
    if i == 0:
        str = S[0]
    elif s.count(S[i]) > s.count(str):
        str = S[i]

print(str)