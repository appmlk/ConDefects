N,X = map(int,input().split())

S = input()

Move =[]

for i in range(N):
    if len(Move) == 0:
        Move.append(S[i])
    else:
        if S[i]== "L" or S[i] == "R":
            Move.append(S[i])
        else:
            if Move[-1]=="L" or Move[-1] =="R":
                Move.pop()
            else:
                Move.append("U")

for s in Move:
    if s == "L":
        X*=2
    elif s == "R":
        X*=2+1
    else:
        X//=2
print(X)