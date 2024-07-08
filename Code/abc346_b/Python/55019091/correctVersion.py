white, black = map(int,input().split())
while white > 6 and black > 4 :
    white -= 7
    black -= 5
#print(white, black)
if abs(white - black) >= 4:
    print("No")
    exit()

#print(white,black)
s = "wbwbwwbwbwbwwbwbwwbwbwbw"
S = list(s)
S_dict = {"w":0, "b":0}

for wb in range(white + black):
    S_dict[S[wb]] += 1
#print(S_dict)
for wb in range(24 - white - black):
    if S_dict["w"] == white and S_dict["b"] == black:
        print("Yes")
        exit()
    S_dict[S[wb]] -= 1
    S_dict[S[wb + white + black]] += 1
    #print(S_dict)

print("No")