#@markdown # 04 Tires

text=list(input())

lasttext="".join(text[-1:-2])
if lasttext=="er":
    print("er")
else:
    print("ist")