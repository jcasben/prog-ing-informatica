use std::env;

fn main() {
    let mut hx: f32 = 0.0;
    let mut k: f32 = 0.0;
    let mut l: f32 = 0.0;
    let n: f32;

    // We collect the params that the user introduced
    let args: Vec<String> = env::args().collect();
    //If there isn't the correct number of params, we finish the program
    if args.len() < 3 {
        println!("Please, introduce the correct params!");
        return;
    }

    //Mapping the first param (probabilities) from String to f32
    let pi: Vec<f32> = args[1]
        .split(',')
        .map(|s| {
            s.trim()
                .parse()
                .unwrap_or_else(|_| panic!("Couldn't be parsed to f32: {}", s))
        }).collect();

    //Mapping the second param (code lengths) from String to f32
    let li: Vec<f32> = args[2]
        .split(',')
        .map(|s| {
            s.trim()
                .parse()
                .unwrap_or_else(|_| panic!("Couldn't be parsed to f32: {}", s))
        }).collect();

    //If the lengths of the params doesn't match, we finish the program
    if pi.len() != li.len() {
        println!("The params' length must match!");
        return;
    }

    //Calculate S
    let s = li.len();

    //Calculate H(x), L and k
    for i in 0..li.len() {
        hx += pi[i] * f32::log2(1.0/pi[i]);
        l += pi[i] * li[i];
        k += 1.0/2_i32.pow(li[i] as u32) as f32;
    }

    //Calculate the efficiency of the code
    n = hx/l;

    //print results
    println!("-- FONT CHARACTERIZATION --");
    println!("S = {s}");
    println!("H(x) = {:.3}", hx);
    println!("-- CODE CHARACTERIZATION --");
    println!("k = {:.4}", k);
    println!("L = {:.4}", l);
    println!("n = {:.4}", n);
}
