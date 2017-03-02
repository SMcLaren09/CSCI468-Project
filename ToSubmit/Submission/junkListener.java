// Generated from junk.g4 by ANTLR 4.6
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link junkParser}.
 */
public interface junkListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link junkParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(junkParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(junkParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(junkParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(junkParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#pgm_body}.
	 * @param ctx the parse tree
	 */
	void enterPgm_body(junkParser.Pgm_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#pgm_body}.
	 * @param ctx the parse tree
	 */
	void exitPgm_body(junkParser.Pgm_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(junkParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(junkParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#string_decl}.
	 * @param ctx the parse tree
	 */
	void enterString_decl(junkParser.String_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#string_decl}.
	 * @param ctx the parse tree
	 */
	void exitString_decl(junkParser.String_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#str}.
	 * @param ctx the parse tree
	 */
	void enterStr(junkParser.StrContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#str}.
	 * @param ctx the parse tree
	 */
	void exitStr(junkParser.StrContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void enterVar_decl(junkParser.Var_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void exitVar_decl(junkParser.Var_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#var_type}.
	 * @param ctx the parse tree
	 */
	void enterVar_type(junkParser.Var_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#var_type}.
	 * @param ctx the parse tree
	 */
	void exitVar_type(junkParser.Var_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#any_type}.
	 * @param ctx the parse tree
	 */
	void enterAny_type(junkParser.Any_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#any_type}.
	 * @param ctx the parse tree
	 */
	void exitAny_type(junkParser.Any_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#id_list}.
	 * @param ctx the parse tree
	 */
	void enterId_list(junkParser.Id_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#id_list}.
	 * @param ctx the parse tree
	 */
	void exitId_list(junkParser.Id_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#id_tail}.
	 * @param ctx the parse tree
	 */
	void enterId_tail(junkParser.Id_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#id_tail}.
	 * @param ctx the parse tree
	 */
	void exitId_tail(junkParser.Id_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#param_decl_list}.
	 * @param ctx the parse tree
	 */
	void enterParam_decl_list(junkParser.Param_decl_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#param_decl_list}.
	 * @param ctx the parse tree
	 */
	void exitParam_decl_list(junkParser.Param_decl_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#param_decl}.
	 * @param ctx the parse tree
	 */
	void enterParam_decl(junkParser.Param_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#param_decl}.
	 * @param ctx the parse tree
	 */
	void exitParam_decl(junkParser.Param_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#param_decl_tail}.
	 * @param ctx the parse tree
	 */
	void enterParam_decl_tail(junkParser.Param_decl_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#param_decl_tail}.
	 * @param ctx the parse tree
	 */
	void exitParam_decl_tail(junkParser.Param_decl_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#func_declarations}.
	 * @param ctx the parse tree
	 */
	void enterFunc_declarations(junkParser.Func_declarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#func_declarations}.
	 * @param ctx the parse tree
	 */
	void exitFunc_declarations(junkParser.Func_declarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#func_decl}.
	 * @param ctx the parse tree
	 */
	void enterFunc_decl(junkParser.Func_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#func_decl}.
	 * @param ctx the parse tree
	 */
	void exitFunc_decl(junkParser.Func_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#func_body}.
	 * @param ctx the parse tree
	 */
	void enterFunc_body(junkParser.Func_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#func_body}.
	 * @param ctx the parse tree
	 */
	void exitFunc_body(junkParser.Func_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterStmt_list(junkParser.Stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitStmt_list(junkParser.Stmt_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(junkParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(junkParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#base_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBase_stmt(junkParser.Base_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#base_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBase_stmt(junkParser.Base_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssign_stmt(junkParser.Assign_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssign_stmt(junkParser.Assign_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#assign_expr}.
	 * @param ctx the parse tree
	 */
	void enterAssign_expr(junkParser.Assign_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#assign_expr}.
	 * @param ctx the parse tree
	 */
	void exitAssign_expr(junkParser.Assign_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#read_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRead_stmt(junkParser.Read_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#read_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRead_stmt(junkParser.Read_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#write_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWrite_stmt(junkParser.Write_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#write_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWrite_stmt(junkParser.Write_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturn_stmt(junkParser.Return_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturn_stmt(junkParser.Return_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(junkParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(junkParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#expr_prefix}.
	 * @param ctx the parse tree
	 */
	void enterExpr_prefix(junkParser.Expr_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#expr_prefix}.
	 * @param ctx the parse tree
	 */
	void exitExpr_prefix(junkParser.Expr_prefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(junkParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(junkParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#factor_prefix}.
	 * @param ctx the parse tree
	 */
	void enterFactor_prefix(junkParser.Factor_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#factor_prefix}.
	 * @param ctx the parse tree
	 */
	void exitFactor_prefix(junkParser.Factor_prefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#postfix_expr}.
	 * @param ctx the parse tree
	 */
	void enterPostfix_expr(junkParser.Postfix_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#postfix_expr}.
	 * @param ctx the parse tree
	 */
	void exitPostfix_expr(junkParser.Postfix_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#call_expr}.
	 * @param ctx the parse tree
	 */
	void enterCall_expr(junkParser.Call_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#call_expr}.
	 * @param ctx the parse tree
	 */
	void exitCall_expr(junkParser.Call_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list(junkParser.Expr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list(junkParser.Expr_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#expr_list_tail}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_tail(junkParser.Expr_list_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#expr_list_tail}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_tail(junkParser.Expr_list_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(junkParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(junkParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#addop}.
	 * @param ctx the parse tree
	 */
	void enterAddop(junkParser.AddopContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#addop}.
	 * @param ctx the parse tree
	 */
	void exitAddop(junkParser.AddopContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#mulop}.
	 * @param ctx the parse tree
	 */
	void enterMulop(junkParser.MulopContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#mulop}.
	 * @param ctx the parse tree
	 */
	void exitMulop(junkParser.MulopContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(junkParser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(junkParser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#else_part}.
	 * @param ctx the parse tree
	 */
	void enterElse_part(junkParser.Else_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#else_part}.
	 * @param ctx the parse tree
	 */
	void exitElse_part(junkParser.Else_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(junkParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(junkParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#compop}.
	 * @param ctx the parse tree
	 */
	void enterCompop(junkParser.CompopContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#compop}.
	 * @param ctx the parse tree
	 */
	void exitCompop(junkParser.CompopContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stmt(junkParser.While_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stmt(junkParser.While_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link junkParser#tokens}.
	 * @param ctx the parse tree
	 */
	void enterTokens(junkParser.TokensContext ctx);
	/**
	 * Exit a parse tree produced by {@link junkParser#tokens}.
	 * @param ctx the parse tree
	 */
	void exitTokens(junkParser.TokensContext ctx);
}