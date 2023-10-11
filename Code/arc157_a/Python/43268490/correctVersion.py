N, A, B, C, D = map(int, input().split())

# XX, XYは(start)X, XX, YXの後にのみ続く
# YX, YYは(start)Y, XY, YYの後にのみ続く

# A, Bの個数はA, Cの個数とほぼ同じ -> Bの個数はCの個数とほぼ同じ
# C, Dの個数はB, Dの個数とほぼ同じ -> Cの個数はBの個数とほぼ同じ

if abs(B-C) == 1:
    print("Yes")
elif B==C and B!=0:
    print("Yes")
elif B==C and (A==0 or D==0): # B=C=0
    print("Yes")
else:
    print("No")