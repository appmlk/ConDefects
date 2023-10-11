N = int(input())
S = input()

while "A" in S:
    S = S.replace("A", "B")
while "BB" in S:
    S = S.replace("BB", "A")
print(S)
