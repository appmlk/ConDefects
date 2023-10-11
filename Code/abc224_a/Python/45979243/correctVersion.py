#@markdown # 04 Tires

text=list(input())

lasttext="".join(text[-2:])
if lasttext=="er":
    print("er")
else:
    print("ist")