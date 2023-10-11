N = int(input())
S = input()

while "A" in S:
    S = S.replace("A", "BB")
while "BB" in S:
    S = S.replace("BB", "A")
print(S)
